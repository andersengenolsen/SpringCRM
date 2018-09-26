package springcrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springcrm.dao.RoleDao;
import springcrm.entity.Role;

import java.util.List;

/**
 * Service for accessing the Roles in the database
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * @param name of role
     * @return role with given name, null if not found
     */
    @Override
    @Transactional
    public Role findRoleByName(String name) {
        return roleDao.findRoleByName(name);
    }

    /**
     * @return All roles in Role table
     */
    @Override
    @Transactional
    public List<Role> getAll() {
        return roleDao.getAll();
    }

    /**
     * @param role to save to db
     */
    @Override
    @Transactional
    public void save(Role role) {
        roleDao.save(role);
    }

    /**
     * @param id id of role
     * @return role with id, null if not found
     */
    @Override
    @Transactional
    public Role get(int id) {
        return roleDao.get(id);
    }

    /**
     * @param id to delete
     */
    @Override
    @Transactional
    public void delete(int id) {
        roleDao.delete(id);
    }

    /**
     * @param T object to delete
     */
    @Override
    @Transactional
    public void delete(Role T) {
        roleDao.delete(T);
    }
}
