package jsuszynski.login.servlets;


import jsuszynski.login.domain.Role;
import jsuszynski.login.domain.User;
import jsuszynski.login.repository.RoleRepository;
import jsuszynski.login.repository.UsersRepository;
import jsuszynski.login.tools.PasswordHash;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Inject
    private UsersRepository usersRepository;
    @Inject
    private RoleRepository roleRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (areFieldsEmpty(req, resp)) return;

        if (doesUserExist(req, resp)) return;

        registerUserWithRole(req, resp);
        return;
    }

    private boolean areFieldsEmpty(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("login").isEmpty() //check if user has left some fields empty
                || req.getParameter("password").isEmpty() ){

            req.setAttribute("errorMessage", "Wypełnij wszystkie pola");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/register.jsp");
            requestDispatcher.forward(req, resp);
            return true;
        }

        return false;
    }

    private boolean doesUserExist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (usersRepository.doesExist(req.getParameter("login"))) { //check if user already exists


            req.setAttribute("errorMessage", "Użytkownik z podanym loginem istnieje");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/register.jsp");
            requestDispatcher.forward(req, resp);

            return true;
        }

        return false;
    }

    private void registerUserWithRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        usersRepository.register(new User(req.getParameter("login"), PasswordHash.hashPassword(req.getParameter("password")))); //add user to db

        User user = usersRepository.findUserByLogin(req.getParameter("login"));

        roleRepository.register(new Role(user.getLogin() //add role to db with new users login and id
                , "user"
                , "user"
                , user.getId()));

        req.setAttribute("message", "Użytkownik zarejestrowany pomyślnie");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");

        requestDispatcher.forward(req, resp);
    }
}
