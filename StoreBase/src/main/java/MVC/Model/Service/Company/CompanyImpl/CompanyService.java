package MVC.Model.Service.Company.CompanyImpl;

import MVC.Model.DAO.Company.CompanyDAO;
import MVC.Model.DAO.Company.CompanyDAOImpl.CompanyDAOImpl;
import MVC.Model.Entity.Company.Company;
import MVC.Model.Service.Company.ICompanyService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by oleg on 23.05.2015.
 */

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class CompanyService implements ICompanyService{

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CompanyDAO companyDAO = new CompanyDAOImpl(sessionFactory);

    @Override
    public void insert(Company company) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        companyDAO.insert(company);
    }

    @Override
    public void update(Company company) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        companyDAO.update(company);
    }

    @Override
    public void delete(Company company) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        companyDAO.delete(company);
    }

    @Override
    public Company getById(int id) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return companyDAO.getById(id);
    }

    @Override
    public List<Company> getAllByType(int type_id) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return companyDAO.getAllByType(type_id);
    }

    @Override
    public List<Company> getAll(int directorId) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return companyDAO.getAll(directorId);
    }
}
