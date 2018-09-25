package springcrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springcrm.entity.User;
import springcrm.service.UserService;
import springcrm.user.AppUser;

import java.util.List;

/**
 * Controller for /admin.
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends FormController {

    final static String ADMIN_URL = "/admin";
    final static String ADMIN_VIEW = "admin-panel";
    final static String REDIRECT_ADMIN_VIEW = "redirect:/admin";
    final static String UPDATE_USER_URL = "/user/update-user";
    final static String DELETE_USER_URL = "/user/delete-user";

    @Autowired
    private UserService userService;

    /**
     * Returning admin view, with list of all registered users.
     *
     * @param model Model model
     * @return admin panel, with list of all users.
     */
    @GetMapping
    public String showAdminPanel(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return ADMIN_VIEW;
    }

    @GetMapping(UPDATE_USER_URL)
    public String updateUser() {
        // TODO: Add view mapping
        return null;
    }

    @GetMapping(DELETE_USER_URL)
    public String deleteUser(@RequestParam("userId") int id) {
        userService.delete(id);
        return REDIRECT_ADMIN_VIEW;
    }
}
