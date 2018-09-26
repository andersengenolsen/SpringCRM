package springcrm.dao;

import springcrm.entity.Role;

public interface RoleDao {

    /**
     * @param name role name
     * @return Role with given name, null if not found
     */
    Role findRoleByName(String name);

}
