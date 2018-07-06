package jsuszynski.contacts.servlets;

import jsuszynski.contacts.repository.ContactsRepository;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    @Inject
    private ContactsRepository contactsRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        contactsRepository.removeContactById(Long.parseLong(req.getParameter("id")));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/contacts");
        try {
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
