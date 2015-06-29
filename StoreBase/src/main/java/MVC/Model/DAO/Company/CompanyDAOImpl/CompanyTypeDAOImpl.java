package MVC.Model.DAO.Company.CompanyDAOImpl;

import MVC.Model.DAO.Company.CompanyTypeDAO;
import MVC.Model.Entity.Company.CompanyType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oleg on 23.05.2015.
 */

@Repository
public class CompanyTypeDAOImpl implements CompanyTypeDAO {

    private SessionFactory sessionFactory;

    public CompanyTypeDAOImpl() {
    }

    @Autowired
    public CompanyTypeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(CompanyType companyType) {
        sessionFactory.getCurrentSession().save(companyType);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    public void delete(CompanyType companyType) {
        sessionFactory.getCurrentSession().delete(companyType);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    public CompanyType getById(int id) {
        return (CompanyType)sessionFactory.getCurrentSession().get(CompanyType.class, id);
    }

    @Override
    public List<CompanyType> getAll() {
        return (List<CompanyType>)sessionFactory.getCurrentSession().createCriteria(CompanyType.class).list();
    }
}
