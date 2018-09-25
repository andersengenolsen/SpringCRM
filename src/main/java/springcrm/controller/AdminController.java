package springcrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    final static String ADMIN_URL = "/admin";
    final static String ADMIN_VIEW = "admin-panel";

    @GetMapping
    public String showAdminPanel() {
        return ADMIN_VIEW;
    }
}
