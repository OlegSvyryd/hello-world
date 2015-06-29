package Aspects.SessionAspects.Store;

import MVC.Model.Entity.Store.Store;
import MVC.Model.Entity.Users.Users;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.security.Principal;

/**
 * Created by oleg on 24.05.2015.
 */

@Aspect
@Component
public class AddStoreA {

    @Autowired
    private SessionFactory sessionFactory;

    @Before("execution(* MVC.Controller.Store.AddStoreC.addStorePost(..))")
    public void startTransaction(){
        if(!sessionFactory.openSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().beginTransaction();
    }

    @AfterReturning("execution(* MVC.Controller.Store.AddStoreC.addStorePost(..))")
    public void endTransaction(){
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @AfterThrowing("execution(* MVC.Controller.Store.AddStoreC.addStorePost(..))")
    public void showError(){
        sessionFactory.getCurrentSession().getTransaction().rollback();
        System.out.println("AddStoreA error");
    }
}
