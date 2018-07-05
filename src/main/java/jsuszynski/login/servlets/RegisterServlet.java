package jsuszynski.login.servlets;


import jsuszynski.login.domain.User;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (areFieldsEmpty(req, resp)) return;

        if (doesUserExist(req, resp)) return;

        registerUserWithRole(req, resp);
    }

    private boolean areFieldsEmpty(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("login").isEmpty() //check if user has left some fields empty
                || req.getParameter("password").isEmpty()) {

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

        req.setAttribute("message", "Użytkownik zarejestrowany pomyślnie");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");

        requestDispatcher.forward(req, resp);
    }
}
