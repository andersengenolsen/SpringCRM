package springcrm.dao;

import springcrm.entity.Role;

public interface RoleDao {

    public Role findRoleByName(String theRoleName);

}
