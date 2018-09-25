package springcrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static springcrm.config.AppSecurityConfig.DENIED_PAGE;
import static springcrm.config.AppSecurityConfig.LOGIN_PAGE;

/**
 * Redirecting user to login page if unauthorized.
 */
@Controller
public class LoginController {

    final static String LOGIN_VIEW = "login-page";
    final static String DENIED_VIEW = "denied-page";

    @GetMapping(LOGIN_PAGE)
    public String showLoginPage() {
        return LOGIN_VIEW;
    }

    @GetMapping(DENIED_PAGE)
    public String showErrorPage() {
        return DENIED_VIEW;
    }
}
