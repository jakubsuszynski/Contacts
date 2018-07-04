package jsuszynski.contacts.servlets;

import jsuszynski.contacts.domain.Contact;
import jsuszynski.contacts.domain.ContactBuilder;
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

@WebServlet("/addContact")
public class AddContactServlet extends HttpServlet {
    @Inject
    ContactsRepository contactsRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (doesEmailExist(req.getParameter("email"))) {
            req.setAttribute("errorMessage", "Email znajduje się już w bazie kontaktów");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addContact.jsp");
            requestDispatcher.forward(req, resp);
            return;
        }
        Contact contact = new ContactBuilder()
                .setCategory(req.getParameter("category"))
                .setDob(LocalDate.parse(req.getParameter("dob")))
                .setEmail(req.getParameter("email"))
                .setName(req.getParameter("name"))
                .setSubcategory(req.getParameter("subcategory"))
                .setSurname(req.getParameter("surname"))
                .setTelephone(req.getParameter("telephone"))
                .setPassword(req.getParameter("password"))
                .createContact();
        contactsRepository.addContact(contact);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req, resp);
    }

    public boolean doesEmailExist(String email) {
        return Optional.ofNullable(contactsRepository.findByEmail(email)).isPresent();
    }
}
