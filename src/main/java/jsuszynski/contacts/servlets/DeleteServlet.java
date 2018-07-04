package jsuszynski.contacts.servlets;

import jsuszynski.contacts.repository.ContactsRepository;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    @Inject
    ContactsRepository contactsRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        contactsRepository.removeContactById(Long.parseLong(req.getParameter("id")));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/contacts");
        requestDispatcher.forward(req, resp);
    }
}
