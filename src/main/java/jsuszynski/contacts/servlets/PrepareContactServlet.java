package jsuszynski.contacts.servlets;

import jsuszynski.contacts.domain.Contact;
import jsuszynski.contacts.repository.ContactsRepository;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/prepareContact")
public class PrepareContactServlet extends HttpServlet {
    @Inject
    ContactsRepository contactsRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Contact contact = contactsRepository.findById(Long.parseLong(req.getParameter("id")));

        req.getSession().setAttribute("contact", contact);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addContact.jsp");
        requestDispatcher.forward(req, resp);
    }
}
