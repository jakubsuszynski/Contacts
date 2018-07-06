package jsuszynski.contacts.servlets;

import jsuszynski.contacts.domain.Contact;
import jsuszynski.contacts.repository.ContactsRepository;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/contacts")
public class ContactsServlet extends HttpServlet {
    @Inject
    private ContactsRepository contactsRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<Contact> contacts = contactsRepository.getAllContacts();
        req.setAttribute("contacts", contacts);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("contacts.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
