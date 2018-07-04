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

        if (userLogin(req, resp)) return;

        if (req.getHeader("Referer").contains("login.jsp")) {
            resp.sendRedirect("/index.jsp");
            return;
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    private boolean userLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (areFieldsEmpty(req, resp)) return true;

        try {
            req.login(req.getParameter("login"), req.getParameter("password"));
            req.getSession().setAttribute("user", usersRepository.findUserByLogin(req.getParameter("login")));
        } catch (ServletException e) {
            req.setAttribute("errorMessage", "Wrong login or password");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(req, resp);
            return true;
        }
        return false;
    }

    private boolean areFieldsEmpty(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("login").isEmpty() || req.getParameter("password").isEmpty()) {

            req.setAttribute("errorMessage", "Provide all data");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(req, resp);

            return true;
        }
        return false;
    }
}