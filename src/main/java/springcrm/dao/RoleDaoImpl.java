package springcrm.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springcrm.entity.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * @param name role name
     * @return Role with given name, null if not found
     */
    @Override
    public Role findRoleByName(String name) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Role> q = currentSession
                .createQuery("from Role where name=:roleName", Role.class);
        q.setParameter("roleName", name);

        return q.getSingleResult();
    }
}
