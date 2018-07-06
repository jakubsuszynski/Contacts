package jsuszynski.contacts.tools;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequirementsUtil {
    private RequirementsUtil() {
    }

    public static boolean checkPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getParameter("password").matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$")) {
            redirect(req, resp, "\"Hasło musi zawierać co najmniej jedną wielką literę i cyfrę oraz składać się z 8 znaków\"");
            return true;
        }
        return false;
    }

    public static boolean checkTelephone(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getParameter("telephone").matches("^(?=.*\\d)[0-9]{4,}$")) {
            redirect(req, resp, "Numer telefonu za krótki");
            return true;
        }
        return false;
    }

    public static boolean checkEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getParameter("email").contains("@")) {
            redirect(req, resp, "Podano nieprawidłowy email");
            return true;
        }
        return false;
    }

    public static void redirect(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException, IOException {
        req.setAttribute("message", message);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/form.jsp");
        requestDispatcher.forward(req, resp);
    }
}
