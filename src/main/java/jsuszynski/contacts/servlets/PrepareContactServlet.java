package jsuszynski.contacts.servlets;

import jsuszynski.contacts.domain.Contact;
import jsuszynski.contacts.repository.ContactsRepository;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/prepareContact")
public class PrepareContactServlet extends HttpServlet {
    @Inject
    private ContactsRepository contactsRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        Contact contact = contactsRepository.findById(Long.parseLong(req.getParameter("id")));

        req.getSession().setAttribute("contact", contact);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/form.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
