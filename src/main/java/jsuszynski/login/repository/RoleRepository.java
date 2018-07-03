package jsuszynski.login.repository;



import jsuszynski.login.domain.Role;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class RoleRepository {
    @PersistenceContext(unitName = "contacts")
    private EntityManager entityManager;


    public void register(Role role) {
        entityManager.persist(role);
    }

}
