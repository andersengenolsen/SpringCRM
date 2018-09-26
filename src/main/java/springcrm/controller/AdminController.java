package springcrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springcrm.entity.User;
import springcrm.service.UserService;

import javax.validation.Valid;
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
    final static String MODEL_ATTR_USER = "user";
    final static String MODEL_ATTR_USERS = "users";

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
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
        model.addAttribute(MODEL_ATTR_USER, u);
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
        model.addAttribute(MODEL_ATTR_USER, new User());
        return USER_FORM_VIEW;
    }

    /**
     * Handling posts against UPDATE_USER_URL.
     * Saving user to the database.
     * If invalid input, form is simply returned
     *
     * @param user   The user from the form
     * @param result result, check for errors
     * @param model  model form
     * @return view
     */
    @PostMapping(UPDATE_USER_URL)
    public String saveUser(@Valid @ModelAttribute(MODEL_ATTR_USER) User user,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute(MODEL_ATTR_USER, user);
            return USER_FORM_VIEW;
        }
        if (!user.getPassword().equals(user.getPasswordVerif())) {
            model.addAttribute(MODEL_ATTR_USER, user);
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
