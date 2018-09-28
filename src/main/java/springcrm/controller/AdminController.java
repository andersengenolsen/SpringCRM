package springcrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springcrm.entity.Role;
import springcrm.entity.User;
import springcrm.model.AppUser;
import springcrm.model.FormModel;
import springcrm.service.RoleService;
import springcrm.service.UserService;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Controller for /admin.
 */
@Controller
@RequestMapping("/admin/")
public class AdminController {

    final static String ADMIN_URL = "/admin/";
    final static String ADMIN_VIEW = "admin-panel";
    private final static String USER_FORM_VIEW = "user-form";
    private final static String REDIRECT_ADMIN_VIEW = "redirect:/admin/";
    private final static String UPDATE_USER_URL = "user/update-user";
    private final static String DELETE_USER_URL = "user/delete-user";
    private final static String ADD_USER_URL = "user/add-user";
    private final static String MODEL_ATTR_USERS = "users";
    private final static String MODEL_ATTR_FORM = "model";

    /**
     * {@link UserService}
     */
    private UserService userService;

    /**
     * {@link RoleService}
     */
    private RoleService roleService;

    /**
     * All roles, fetched from database
     */
    private LinkedHashMap<String, Role> allRoles;

    /**
     * {@link FormModel}
     */
    private FormModel formModel;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    /**
     * Fetching roles from database,
     * and setting up the {@link FormModel}
     */
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
        List<AppUser> users = appUsersFromUsers(userService.getAll());

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
        AppUser u = appUserFromUser(userService.get(id));

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
        formModel.setUser(new AppUser());
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

        AppUser appUser = form.getUser();

        if (result.hasErrors()) {
            form.setAllRoles(allRoles);
            model.addAttribute(MODEL_ATTR_FORM, form);
            return USER_FORM_VIEW;
        }

        if (!appUser.getPassword().equals(appUser.getPasswordVerif())) {
            form.setAllRoles(allRoles);
            model.addAttribute(MODEL_ATTR_FORM, form);
            model.addAttribute("error", "Passwords must match");
            return USER_FORM_VIEW;
        }

        User user = userFromAppUser(appUser);

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

    /**
     * Converting AppUser to User
     *
     * @param appUser {@link AppUser}
     * @return User
     */
    private User userFromAppUser(AppUser appUser) {
        User user = new User();
        user.setPassword(appUser.getPassword());
        user.setUsername(appUser.getUsername());
        user.setRole(allRoles.get(appUser.getFormRole()));

        if (appUser.getId() != null)
            user.setId(appUser.getId());

        return user;
    }

    /**
     * Converting User to AppUser
     *
     * @param user {@link User}
     * @return
     */
    private AppUser appUserFromUser(User user) {
        AppUser appUser = new AppUser();
        appUser.setPassword(user.getPassword());
        appUser.setPasswordVerif(user.getPassword());
        appUser.setUsername(user.getUsername());

        appUser.setFormRole(user.getRole().getName());

        if (user.getId() != null)
            appUser.setId(user.getId());

        return appUser;
    }

    /**
     * Converting list of User to Appuser
     *
     * @param users
     * @return List of appusers
     */
    private List<AppUser> appUsersFromUsers(List<User> users) {
        List<AppUser> list = new ArrayList<>();

        for (User u : users)
            list.add(appUserFromUser(u));

        return list;
    }

    /**
     * Pre processing all web requests.
     * Stripping whitespace from Strings.
     * Only whitespace will be changed to null.
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
