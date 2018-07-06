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
    public static final String PASSWORD_PARAM = "password";
    public static final String LOGIN_PARAM = "login";
    public static final String MESSAGE_PARAM = "message";
    @Inject
    private UsersRepository usersRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (areFieldsEmpty(req, resp) || doesUserExist(req, resp) || checkPassword(req, resp)) return;
            registerUserWithRole(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerUserWithRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //add user to db
        usersRepository.register(new User(req.getParameter(LOGIN_PARAM), PasswordHash.hashPassword(req.getParameter(PASSWORD_PARAM))));
        req.setAttribute(MESSAGE_PARAM, "Użytkownik zarejestrowany pomyślnie");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req, resp);
    }

    private boolean areFieldsEmpty(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //checkRequirements if user has left some fields empty
        if (req.getParameter(LOGIN_PARAM).isEmpty() || req.getParameter(PASSWORD_PARAM).isEmpty()) {
            redirect(req, resp, "Wypełnij wszystkie pola");
            return true;
        }
        return false;
    }

    private boolean doesUserExist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (usersRepository.doesExist(req.getParameter(LOGIN_PARAM))) { //checkRequirements if user already exists
            redirect(req, resp, "Użytkownik z podanym loginem istnieje");
            return true;
        }
        return false;
    }

    private boolean checkPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //regex for 8 chars, one digit, one lower and one upper letter
        if (!req.getParameter(PASSWORD_PARAM).matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$")) {
            redirect(req, resp, "Hasło musi zawierać co najmniej jedną wielką literę i cyfrę oraz składać się z 8 znaków");
            return true;
        }
        return false;
    }

    private void redirect(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException, IOException {

        req.setAttribute(MESSAGE_PARAM, message);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/register.jsp");
        requestDispatcher.forward(req, resp);
    }
}
