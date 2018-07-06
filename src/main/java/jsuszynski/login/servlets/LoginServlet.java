package jsuszynski.login.servlets;


import jsuszynski.login.repository.UsersRepository;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public static final String LOGIN_PARAM = "login";
    public static final String PASSWORD_PARAM = "password";
    public static final String MESSAGE_PARAM = "message";
    @Inject
    private UsersRepository usersRepository;

    private boolean loginUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            req.login(req.getParameter(LOGIN_PARAM), req.getParameter(PASSWORD_PARAM)); //login user and set him in session
            req.getSession().setAttribute("user", usersRepository.findUserByLogin(req.getParameter(LOGIN_PARAM)));
        } catch (ServletException e) {
            redirect(req, resp, "Niepoprawny login lub hasło");
            return false;
        }
        return true;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (areFieldsEmpty(req, resp) || !loginUser(req, resp)) { //if user has left empty fields or login failed - stop servlet
                return;
            }
            resp.sendRedirect("/index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean areFieldsEmpty(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter(LOGIN_PARAM).isEmpty() || req.getParameter(PASSWORD_PARAM).isEmpty()) {
            redirect(req, resp, "Wypełnij wszystkie pola");
            return true;
        }
        return false;
    }

    private void redirect(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException, IOException {
        req.setAttribute(MESSAGE_PARAM, message);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req, resp);
    }
}