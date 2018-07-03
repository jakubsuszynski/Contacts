package jsuszynski.login.repository;



import jsuszynski.login.domain.User;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
public class UsersRepository {
    @PersistenceContext(unitName = "contacts")
    private EntityManager entityManager;

    public User findUserByLogin(String login) {
        return (User) entityManager.createQuery("from User u where u.login=:login")
                .setParameter("login", login)
                .getSingleResult();
    }

    public User findUserByEmail(String email) {
        return (User) entityManager.createQuery("from User u where u.email=:email")
                .setParameter("email", email)
                .getSingleResult();
    }

    public List<User> getAllUsers() {
        return entityManager.createQuery("from User").getResultList();
    }

    public void register(User user) {
        entityManager.persist(user);
    }

}
