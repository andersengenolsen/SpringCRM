package springcrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springcrm.entity.Role;
import springcrm.entity.User;
import springcrm.model.FormModel;
import springcrm.service.RoleService;
import springcrm.service.UserService;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Controller for /admin.
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends FormController {

    final static String ADMIN_URL = "/admin";
    final static String ADMIN_VIEW = "admin-panel";
    final static String USER_FORM_VIEW = "user-form";
    final static String REDIRECT_ADMIN_VIEW = "redirect:/admin";
    final static String UPDATE_USER_URL = "/user/update-user";
    final static String DELETE_USER_URL = "/user/delete-user";
    final static String ADD_USER_URL = "/user/add-user";
    final static String MODEL_ATTR_USERS = "users";
    final static String MODEL_ATTR_FORM = "model";

    private UserService userService;

    private RoleService roleService;

    private LinkedHashMap<String, Role> allRoles;

    private FormModel formModel;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    protected void setUp() {
        List<Role> roles = roleService.getAll();

        allRoles = new LinkedHashMap<>();

        for (Role r : roles)
            allRoles.put(r.getName(), r);

        formModel = new FormModel();
    }

    /**
     * Returning admin view, with list of all registered users.
     *
     * @param model Model model
     * @return admin panel, with list of all users.
     */
    @GetMapping
    public String showAdminPanel(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute(MODEL_ATTR_USERS, users);
        return ADMIN_VIEW;
    }

    /**
     * Updating a user
     *
     * @param id    user id
     * @param model form model
     * @return form view
     */
    @GetMapping(UPDATE_USER_URL)
    public String showUpdateForm(@RequestParam("userId") int id, Model model) {
        User u = userService.get(id);
        if (u != null)
            u.setPasswordVerif(u.getPassword());
        formModel.setAllRoles(allRoles);
        formModel.setUser(u);
        model.addAttribute(MODEL_ATTR_FORM, formModel);
        return USER_FORM_VIEW;
    }

    /**
     * Adding new user to the database.
     * Adding empty user to model.
     *
     * @param model
     * @return form view
     */
    @GetMapping(ADD_USER_URL)
    public String showAddUserForm(Model model) {
        formModel.setUser(new User());
        formModel.setAllRoles(allRoles);
        model.addAttribute(MODEL_ATTR_FORM, formModel);
        return USER_FORM_VIEW;
    }

    /**
     * Handling posts against UPDATE_USER_URL.
     * Saving user to the database.
     * If invalid input, form is simply returned
     *
     * @param form   The {@link FormModel}
     * @param result result, check for errors
     * @param model  model form
     * @return view
     */
    @PostMapping(UPDATE_USER_URL)
    public String saveUser(@Valid @ModelAttribute(MODEL_ATTR_FORM) FormModel form,
                           BindingResult result, Model model) {

        User user = form.getUser();

        if (result.hasErrors() || user.getFormRole() == null) {
            model.addAttribute(MODEL_ATTR_FORM, user);
            return USER_FORM_VIEW;
        }

        String formRole = user.getFormRole();
        Role r = allRoles.get(formRole);
        user.addRole(r);

        if (!user.getPassword().equals(user.getPasswordVerif())) {
            model.addAttribute(MODEL_ATTR_FORM, user);
            model.addAttribute("error", "Passwords must match");
            return USER_FORM_VIEW;
        }

        userService.save(user);
        return REDIRECT_ADMIN_VIEW;
    }

    /**
     * Deleting a user from the database
     *
     * @param id user id
     * @return views
     */
    @GetMapping(DELETE_USER_URL)
    public String deleteUser(@RequestParam("userId") int id) {
        userService.delete(id);
        return REDIRECT_ADMIN_VIEW;
    }
}
