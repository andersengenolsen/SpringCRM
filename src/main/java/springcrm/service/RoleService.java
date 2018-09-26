package springcrm.service;

import springcrm.entity.Role;

/**
 * Interface for methods against Role table in database.
 */
public interface RoleService extends Service<Role> {
    /**
     * @param name of role
     * @return Role with name
     */
    Role findRoleByName(String name);
}
