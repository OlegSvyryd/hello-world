package MVC.Controller;

import MVC.Model.Entity.Report.Mailing;
import MVC.Model.Entity.Users.UserRoles;
import MVC.Model.Entity.Users.Users;
import MVC.Model.Service.Users.IUsersService;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigInteger;
import java.security.Principal;
import java.security.SecureRandom;
import java.sql.SQLException;

/**
 * Created by oleg on 18.05.2015.
 */

@Controller
public class WelcomeC {

    @Autowired
    IUsersService usersService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JavaMailSender javaMailSender;

    public static String username;
    public static Users currentUser;

    //Welcome page
    @RequestMapping("/welcome")
    public ModelAndView getWelcomePage() {
        return new ModelAndView("Welcome");
    }

    //Login page
    @RequestMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("LogIn");
    }

    //Registration pages
    //Get method
    @RequestMapping("/registration")
    public ModelAndView getRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView("Registration", "users", new Users());
        return modelAndView;
    }

    //Post method
    @RequestMapping(value = "/registration_during", method = RequestMethod.POST)
    public ModelAndView processRegistration(@Valid @ModelAttribute("users") Users users, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("redirect:login");

        if(bindingResult.hasErrors()){
            ModelAndView modelAndView1 = new ModelAndView("Registration");
            modelAndView.addObject("users", users);
            modelAndView.addObject("error_list", "Помилка, будь-ласка введіть коректні дані");

            return modelAndView1;
        }
        else {
            if (emailExist(users.getEmail())) {
                ModelAndView modelAndView1 = new ModelAndView("Registration");
                modelAndView1.addObject("errorLabelEmail", "Помилка, такий email вже зареєстрований");
                return modelAndView1;
            }
            else if (phoneExist(users.getPhone())) {
                    ModelAndView modelAndView1 = new ModelAndView("Registration");
                    modelAndView1.addObject("errorLabelPhone", "Помилка, такий номер телефону вже є в системі");
                    return modelAndView1;
                }
            else {
                usersService.insert(users, new UserRoles(users, users.getEmail(), "ROLE_USER"));
                return modelAndView;
            }
        }
    }

    //Main page
    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping("/main")
    public ModelAndView getMainPage(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("Main");
        Users user = usersService.getUserByEmail(principal.getName());
        modelAndView.addObject("username", user.getName() + " " + user.getSurname());
        username = user.getName() + " " + user.getSurname();
        currentUser = user;

        return modelAndView;
    }

    //ajax tech support
    @RequestMapping(value = "support", method = RequestMethod.POST)
    public @ResponseBody
    String techSupport(@RequestParam String text) {
        Mailing mailing = new Mailing();
        mailing.setMailSender(javaMailSender);
        mailing.sendMail("robannnnn@gmail.com",
                "1996Oleg@bigmir.net",
                "StoreBase: Технічна підтримка!",
                text);
            return "success";
    }

    @RequestMapping(value = "forgot-password", method = RequestMethod.POST)
    public @ResponseBody
    String forgotPassword(@RequestParam String text) {
        Users newUser = usersService.getUserByEmail(text);
        String pass = new BigInteger(24, new SecureRandom()).toString(24);
        newUser.setPassword(passwordEncoder.encode(pass));
        newUser.setConfirmPassword(passwordEncoder.encode(pass));
        System.out.println(pass);
        usersService.update(newUser);
        Mailing mailing = new Mailing();
        mailing.setMailSender(javaMailSender);
        mailing.sendMail("robannnnn@gmail.com",
                text,
                "StoreBase: Технічна підтримка!",
                "Ваш новий пароль: " + pass);
        return "success";
    }

    //Error page
    @RequestMapping("/403page")
    public String get403denied() {
        return "redirect:welcome?denied";
    }

    private boolean emailExist(String email) {
        Users users = usersService.getUserByEmail(email);
        if (users != null) {
            return true;
        }
        return false;
    }

    private boolean phoneExist(String phone) {
        Users users = usersService.getUserByPhone(phone);
        if (users != null) {
            return true;
        }
        return false;
    }
}
