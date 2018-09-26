package springcrm.model;

import springcrm.entity.Role;
import springcrm.entity.User;

import java.util.Map;

/**
 * Class for use in forms, to be added to as a modelattribute.
 */
public class FormModel {
    private User user;
    private Map<String, Role> allRoles;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, Role> getAllRoles() {
        return allRoles;
    }

    public void setAllRoles(Map<String, Role> allRoles) {
        this.allRoles = allRoles;
    }
}
