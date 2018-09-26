package springcrm.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * Abstract class, providing common methods for form handling controllers
 */
public abstract class FormController {

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
