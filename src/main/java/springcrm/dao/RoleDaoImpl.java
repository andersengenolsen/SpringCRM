package springcrm.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springcrm.entity.Role;

import java.util.List;

/**
 * Repo for operations against the {@link Role} entity
 */
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

    /**
     * @return all roles
     */
    @Override
    public List<Role> getAll() {
        Session curr = sessionFactory.getCurrentSession();
        Query<Role> query = curr.createQuery("from Role order by name"
                , Role.class);
        return query.getResultList();
    }

    /**
     * @param role to save
     */
    @Override
    public void save(Role role) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(role);
    }

    /**
     * @param id of role
     * @return role with id, null if not found
     */
    @Override
    public Role get(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Role.class, id);

    }

    /**
     * @param id Id of object T to delete
     */
    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query =
                session.createQuery("delete from Role where id=:roleId");
        query.setParameter("roleId", id);
        query.executeUpdate();
    }

    /**
     * @param role to delete
     */
    @Override
    public void delete(Role role) {
        if (role.getId() == null)
            throw new IllegalArgumentException("Role must have valid id");

        Session session = sessionFactory.getCurrentSession();
        session.delete(role);
    }
}
