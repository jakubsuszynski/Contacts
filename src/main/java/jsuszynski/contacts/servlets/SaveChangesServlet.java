package jsuszynski.contacts.servlets;

import jsuszynski.contacts.repository.ContactsRepository;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/saveChanges")
public class SaveChangesServlet extends HttpServlet {
    @Inject
    ContactsRepository contactsRepository;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
