package Aspects.SessionAspects.Company;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by oleg on 24.05.2015.
 */

@Aspect
@Component
public class AddCompanyA {

    @Autowired
    private SessionFactory sessionFactory;

    @Before("execution(* MVC.Controller.Company.AddCompanyC.addCompanyPost(..))")
    public void startTransaction(){
        if(!sessionFactory.openSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().beginTransaction();
    }

    @AfterReturning("execution(* MVC.Controller.Company.AddCompanyC.addCompanyPost(..))")
    public void endTransaction(){
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @AfterThrowing("execution(* MVC.Controller.Company.AddCompanyC.addCompanyPost(..))")
    public void showError(){
        sessionFactory.getCurrentSession().getTransaction().rollback();
        System.out.println("AddCompanyA error");
    }
}
