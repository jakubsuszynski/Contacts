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
        return (User) entityManager.createQuery("FROM User u WHERE u.login=:login")
                .setParameter("login", login)
                .getSingleResult();
    }


    public boolean doesExist(String login) {
        List results = entityManager.createQuery("FROM User u WHERE  u.login=:login")
               .setParameter("login", login).getResultList();
        return !results.isEmpty();

    }


    public void register(User user) {
        entityManager.persist(user);
    }

}
