package jsuszynski.contacts.servlets;

import jsuszynski.contacts.domain.Contact;
import jsuszynski.contacts.repository.ContactsRepository;
import jsuszynski.contacts.tools.RequirementsUtil;

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
    private ContactsRepository contactsRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (RequirementsUtil.checkEmail(req, resp) || RequirementsUtil.checkPassword(req, resp) || RequirementsUtil.checkTelephone(req, resp))
                return;

            addContact(req, resp);
            req.setAttribute("message", "Kontakt zapisany");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void addContact(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Contact contact;
        //if optional object exists it means Contact is goind to be modified - we don't create new one
        Optional<Contact> contactOptional = Optional.ofNullable((Contact) req.getSession().getAttribute("contact"));

        contact = contactOptional.orElseGet(Contact::new);
        setParams(req, contact);
        try {
            contactsRepository.persist(contact);
        } catch (Exception e) {
            RequirementsUtil.redirect(req, resp, "Email zajęty");
        }
        req.getSession().removeAttribute("contact");
    }

    private void setParams(HttpServletRequest req, Contact contact) {
        contact.setCategory(req.getParameter("category"));
        contact.setDob(LocalDate.parse(req.getParameter("dob")));
        contact.setEmail(req.getParameter("email"));
        contact.setName(req.getParameter("name"));
        contact.setSubcategory(req.getParameter("subcategory"));
        contact.setSurname(req.getParameter("surname"));
        contact.setTelephone(req.getParameter("telephone"));
        contact.setPassword(req.getParameter("password"));
    }

}
