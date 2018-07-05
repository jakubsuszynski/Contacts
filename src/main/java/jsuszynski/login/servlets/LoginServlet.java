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

    @Inject
    private UsersRepository usersRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (areFieldsEmpty(req, resp) || !loginUser(req, resp)) { //if user has left empty fields or login failed - stop servlet
            return;
        }
        resp.sendRedirect("/index.jsp");
    }

    private boolean areFieldsEmpty(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("login").isEmpty() || req.getParameter("password").isEmpty()) {

            req.setAttribute("errorMessage", "Wypełnij wszystkie pola");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(req, resp);

            return true;
        }
        return false;
    }

    private boolean loginUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            req.login(req.getParameter("login"), req.getParameter("password")); //login user and set him in session
            req.getSession().setAttribute("user", usersRepository.findUserByLogin(req.getParameter("login")));
        } catch (ServletException e) {
            req.setAttribute("errorMessage", "Niepoprawny login lub hasło");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp"); //redirect to main page if login failed
            requestDispatcher.forward(req, resp);
            return false;
        }
        return true;
    }
}