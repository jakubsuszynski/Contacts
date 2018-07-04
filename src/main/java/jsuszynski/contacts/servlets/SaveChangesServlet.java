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
import java.time.LocalDate;

@WebServlet("/saveChanges")
public class SaveChangesServlet extends HttpServlet {
    @Inject
    ContactsRepository contactsRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addContact(req);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req, resp);
    }


    private void addContact(HttpServletRequest req) {
        Contact contact = (Contact) req.getSession().getAttribute("contact");

        contact.setCategory(req.getParameter("category"));
        contact.setDob(LocalDate.parse(req.getParameter("dob")));
        contact.setEmail(req.getParameter("email"));
        contact.setName(req.getParameter("name"));
        contact.setSubcategory(req.getParameter("subcategory"));
        contact.setSurname(req.getParameter("surname"));
        contact.setTelephone(req.getParameter("telephone"));
        contact.setPassword(req.getParameter("password"));
        contactsRepository.editContact(contact);
    }


}
