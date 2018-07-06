package jsuszynski.login.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    public static final String MESSAGE_PARAM = "message";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.logout();
            req.getSession().invalidate();

            req.setAttribute(MESSAGE_PARAM, "Wylogowano");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
