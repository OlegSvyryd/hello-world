package Aspects.SessionAspects.Registration;

import MVC.Model.Entity.Users.Users;
import MVC.Model.Service.Users.IUsersService;
import org.aspectj.lang.annotation.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * Created by oleg on 19.05.2015.
 */

@Aspect
@Component
public class RegistrationA {

    public RegistrationA() {
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SessionFactory sessionFactory;

    @Before("execution(* MVC.Controller.WelcomeC.processRegistration(..)) && args(users, bindingResult)")
    public void startTransaction(Users users, BindingResult bindingResult){
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setEnabled(true);
        sessionFactory.getCurrentSession().beginTransaction();
    }

    @AfterReturning("execution(* MVC.Controller.WelcomeC.processRegistration(..))")
    public void endTransaction(){
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @AfterThrowing("execution(* MVC.Controller.WelcomeC.processRegistration(..))")
    public void showError(){
        sessionFactory.getCurrentSession().getTransaction().rollback();
    }

}
