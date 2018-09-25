package springcrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for "/"
 * <p>
 * Simply redirecting to {@link CustomerController} for now.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String showHome() {
        return "redirect:/customer/list";
    }
}
