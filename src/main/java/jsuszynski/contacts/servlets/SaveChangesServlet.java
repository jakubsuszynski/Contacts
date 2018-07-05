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
import java.util.Optional;

@WebServlet("/persist")
public class SaveChangesServlet extends HttpServlet {
    @Inject
    ContactsRepository contactsRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addContact(req, resp);
        req.setAttribute("message", "Kontakt dodany");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/form.jsp");
        requestDispatcher.forward(req, resp);
    }


    private void addContact(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Contact contact;
        //if optional object exists it means Contact is goind to be modified - we don't create new one
        Optional<Contact> contactOptional = Optional.ofNullable((Contact) req.getSession().getAttribute("contact"));

        if (contactOptional.isPresent()) {
            contact = contactOptional.get();
        } else {
            //in the other way we are creating new Contact, but firstly it's necessary to check if provided email exists in database.
            if (checkIfExists(req, resp)) {
                return;
            }
            contact = new Contact();
        }
        contact.setCategory(req.getParameter("category"));
        contact.setDob(LocalDate.parse(req.getParameter("dob")));
        contact.setEmail(req.getParameter("email"));
        contact.setName(req.getParameter("name"));
        contact.setSubcategory(req.getParameter("subcategory"));
        contact.setSurname(req.getParameter("surname"));
        contact.setTelephone(req.getParameter("telephone"));
        contact.setPassword(req.getParameter("password"));
        try {
            contactsRepository.persist(contact);
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Email zajęty");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/form.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    private boolean checkIfExists(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (doesEmailExist(req.getParameter("email"))) {
            req.setAttribute("errorMessage", "Email znajduje się już w bazie kontaktów");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/form.jsp");
            requestDispatcher.forward(req, resp);
            return true;
        }
        return false;
    }

    public boolean doesEmailExist(String email) {
        return Optional.ofNullable(contactsRepository.findByEmail(email)).isPresent();
    }
}