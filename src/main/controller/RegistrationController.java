package com.infosys.irs.controller;

// =======================
// Spring imports
// =======================

// Enables validation using annotations like @NotNull, @Size, etc.
import javax.validation.Valid;

// NOTE: This import looks incorrect for Spring's Environment.
// Typically it should be:
// import org.springframework.core.env.Environment;
import org.hibernate.cfg.Environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

// =======================
// Local application imports
// =======================
import com.infosys.irs.model.User;
import com.infosys.irs.exception.UserAlreadyExistsException;
import com.infosys.irs.service.RegistrationService;

/**
 * This class handles HTTP requests related to user registration.
 * 
 * @Controller tells Spring this is a web controller (MVC),
 * responsible for returning views (HTML pages).
 */
@Controller
public class RegistrationController {

    /**
     * Service layer dependency.
     * 
     * Spring injects RegistrationService here so the controller
     * does NOT contain business logic.
     */
    @Autowired
    private RegistrationService registrationService;

    /**
     * Environment allows access to values from:
     * - application.properties
     * - application.yml
     * - other configuration sources
     *
     * Used here to fetch messages (success / error messages).
     */
    @Autowired
    private Environment environment;

    // These strings are used as model attribute names and view names
    private String command = "command";
    private String register = "register";

    /**
     * Handles GET requests to /register
     *
     * Purpose:
     * - Show the registration page
     * - Attach an empty User object to the form
     */
    @GetMapping(value = "/register")
    public ModelAndView register(Model model) {

        /*
         * new ModelAndView(viewName, modelAttributeName, modelObject)
         *
         * View name: "register"
         * Model attribute name: "command"
         * Model object: new User()
         *
         * This allows the form to bind input fields to User fields.
         */
        return new ModelAndView(register, command, new User());
    }

    /**
     * Handles POST requests when the registration form is SUBMITTED
     *
     * @Valid triggers validation on the User object
     * @ModelAttribute("command") binds form data to User
     * BindingResult holds validation errors (if any)
     */
    @PostMapping(value = "registerUser")
    public ModelAndView addCustomer(
            @Valid @ModelAttribute("command") User user,
            BindingResult result,
            ModelMap model) {

        ModelAndView modelAndView = new ModelAndView();

        /**
         * If validation fails (e.g. empty fields, invalid data),
         * return the registration page with the same user data.
         */
        if (result.hasErrors()) {

            modelAndView = new ModelAndView(register, command, user);

        } else {

            try {
                /**
                 * Call service layer to register the user.
                 * This may throw UserAlreadyExistsException.
                 */
                registrationService.registerUser(user);

                // Reload the register page
                modelAndView = new ModelAndView(register, command, user);

                // Add a success message from application.properties
                modelAndView.addObject(
                        "successMessage",
                        environment.getProperty("RegistrationController.SUCCESSFUL_REGISTRATION")
                );

            } catch (UserAlreadyExistsException e) {

                /**
                 * Handle custom exception when user already exists.
                 * The message is used as a key to fetch text from properties.
                 */
                if (e.getMessage().contains("RegistrationService")) {

                    modelAndView = new ModelAndView(register);

                    modelAndView.addObject(
                            "message",
                            environment.getProperty(e.getMessage())
                    );
                }
            }
        }

        // Return the final view + model
        return modelAndView;
    }
}
