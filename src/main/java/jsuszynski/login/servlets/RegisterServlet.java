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
import java.util.List;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    public static final String LOGIN = "login";
    public static final String EMAIL = "email";
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

    private void registerUserWithRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        usersRepository.register(new User(req.getParameter(LOGIN), PasswordHash.hashPassword(req)
                , req.getParameter(EMAIL)
                , req.getParameter("name")
                , req.getParameter("surname")));

        User user = usersRepository.findUserByLogin(req.getParameter(LOGIN));

        roleRepository.register(new Role(user.getLogin()
                , "user"
                , "user"
                , user.getId()));

        req.setAttribute("message", "Użytkownik zarejestrowany pomyślnie");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");

        requestDispatcher.forward(req, resp);
    }

    private boolean areFieldsEmpty(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter(LOGIN).isEmpty()
                || req.getParameter("password").isEmpty()
                || req.getParameter(EMAIL).isEmpty()
                || req.getParameter("name").isEmpty()
                || req.getParameter("surname").isEmpty()) {

            req.setAttribute("errorMessage", "Wypełnij wszystkie pola");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/register.jsp");
            requestDispatcher.forward(req, resp);
            return true;
        }

        return false;
    }

    private boolean doesUserExist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> allUsers = usersRepository.getAllUsers();

        if (allUsers.stream().anyMatch(s -> s.getLogin().equals(req.getParameter(LOGIN))
                || s.getEmail().equals(req.getParameter(EMAIL)))) {

            req.setAttribute("errorMessage", "Użytkownik z tym emailem lub loginem istnieje");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/register.jsp");
            requestDispatcher.forward(req, resp);

            return true;
        }

        return false;
    }
}
