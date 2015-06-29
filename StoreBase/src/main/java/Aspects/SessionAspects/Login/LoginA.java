package Aspects.SessionAspects.Login;

import MVC.Model.Entity.Users.Users;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by oleg on 21.05.2015.
 */

@Aspect
@Component
public class LoginA {
    @Autowired
    private SessionFactory sessionFactory;

    @Before("execution(* MVC.Controller.WelcomeC.getMainPage(..))")
    public void afterLogining(){
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().beginTransaction();
    }

    @AfterThrowing("execution(* MVC.Controller.WelcomeC.getMainPage(..))")
    @RequestMapping(value = "/403page")
    public String showError(){
        return "redirect:403page";
    }
}
