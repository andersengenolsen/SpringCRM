package springcrm.model;

import springcrm.entity.Role;

import javax.validation.Valid;
import java.util.Map;

/**
 * Class for use in forms, to be added to as a modelattribute.
 */
public class FormModel {

    @Valid
    private AppUser user;

    private Map<String, Role> allRoles;

    public FormModel() {

    }

    public FormModel(AppUser user) {
        this.user = user;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(@Valid AppUser user) {
        this.user = user;
    }

    public Map<String, Role> getAllRoles() {
        return allRoles;
    }

    public void setAllRoles(Map<String, Role> allRoles) {
        this.allRoles = allRoles;
    }
}
