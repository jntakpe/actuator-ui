package com.github.jntakpe.web;

import com.github.jntakpe.domain.User;
import com.github.jntakpe.service.UserService;
import com.github.jntakpe.util.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

/**
 * Contrôleur gérant les requêtes relatives à l'authentification
 *
 * @author jntakpe
 */
@Controller
public class SecurityController {

    private static final String HOME_VIEW = "home";

    private static final String LOGIN_VIEW = "login";

    private static final String REGISTER_VIEW = "register";

    private final MessageManager messageManager;

    private final UserService userService;

    @Autowired
    public SecurityController(MessageManager messageManager, UserService userService) {
        this.messageManager = messageManager;
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHome() {
        return HOME_VIEW;
    }

    /**
     * Affiche la page de login
     *
     * @param error indication sur l'erreur survenue lors d'une tentative de login précédente
     * @return page de login
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView displayLogin(@RequestParam(required = false) String error) {
        ModelAndView mv = new ModelAndView(LOGIN_VIEW);
        if (error != null) {
            return mv.addObject("errorMsg", messageManager.getMessage("error.auth"));
        }
        return mv;
    }

    /**
     * Affiche de formulaire d'enregistrement
     *
     * @return page d'enregistrement
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView displayRegister() {
        return new ModelAndView(REGISTER_VIEW).addObject(new User());
    }

    /**
     * Création d'un nouvel utilisateur
     *
     * @param user   utilisateur à créer
     * @param result résultat de la validation de l'utilisateur
     * @return page de login en cas de création réussi sinon la page d'enregistrement avec les erreurs
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            if (!user.getPassword().equals(user.getConfirmPassword())) {
                result.addError(new FieldError("user", "confirmPassword", messageManager.getMessage("error.register.confirm.password")));
            }
            return new ModelAndView(REGISTER_VIEW);
        }
        userService.create(user);
        return new ModelAndView(new RedirectView(LOGIN_VIEW));
    }
}
