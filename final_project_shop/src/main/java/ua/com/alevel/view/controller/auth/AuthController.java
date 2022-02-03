package ua.com.alevel.view.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.alevel.config.security.SecurityService;
import ua.com.alevel.facade.util.AuthValidatorFacade;
import ua.com.alevel.facade.util.RegistrationFacade;
import ua.com.alevel.persistence.enums.RoleType;
import ua.com.alevel.util.SecurityUtil;
import ua.com.alevel.view.controller.BaseController;
import ua.com.alevel.view.dto.request.registration.AuthDto;

@Controller
public class AuthController extends BaseController {

    private final RegistrationFacade registrationFacade;
    private final AuthValidatorFacade authValidatorFacade;
    private final SecurityService securityService;

    public AuthController(
            RegistrationFacade registrationFacade,
            AuthValidatorFacade authValidatorFacade,
            SecurityService securityService) {
        this.registrationFacade = registrationFacade;
        this.authValidatorFacade = authValidatorFacade;
        this.securityService = securityService;
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        showMessage(model, false);
        boolean authenticated = securityService.isAuthenticated();
        if (authenticated) {
            if (SecurityUtil.hasRole(RoleType.ROLE_ADMIN.name())) {
                return "redirect:/admin/releases";
            }
            if (SecurityUtil.hasRole(RoleType.ROLE_PERSONAL.name())) {
                return "redirect:/personal";
            }
        }
        if (error != null) {
            showError(model, "Your email and password is invalid.");
        }
        if (logout != null) {
            showInfo(model, "You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return redirectProcess(model);
        }
        model.addAttribute("authForm", new AuthDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("authForm") AuthDto authForm, BindingResult bindingResult, Model model) {
        showMessage(model, false);
        authValidatorFacade.validate(authForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        registrationFacade.registration(authForm);
        securityService.autoLogin(authForm.getEmail(), authForm.getPasswordConfirm());
        return redirectProcess(model);
    }

    private String redirectProcess(Model model) {
        showMessage(model, false);
        if (SecurityUtil.hasRole(RoleType.ROLE_ADMIN.name())) {
            return "redirect:/admin/releases";
        }
        if (SecurityUtil.hasRole(RoleType.ROLE_PERSONAL.name())) {
            return "redirect:/personal";
        }
        return "redirect:/login";
    }
}
