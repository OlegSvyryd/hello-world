package MVC.Model.Service.Company.CompanyImpl;

import MVC.Model.DAO.Company.CompanyDAOImpl.CompanyTypeDAOImpl;
import MVC.Model.DAO.Company.CompanyTypeDAO;
import MVC.Model.Entity.Company.CompanyType;
import MVC.Model.Service.Company.ICompanyTypeService;
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
public class CompanyTypeService implements ICompanyTypeService{

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CompanyTypeDAO companyTypeDAO = new CompanyTypeDAOImpl(sessionFactory);

    @Override
    public void insert(CompanyType companyType) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        companyTypeDAO.insert(companyType);
    }

    @Override
    public void delete(CompanyType companyType) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        companyTypeDAO.delete(companyType);
    }

    @Override
    public CompanyType getById(int id) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return companyTypeDAO.getById(id);
    }

    @Override
    public List<CompanyType> getAll() {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return companyTypeDAO.getAll();
    }
}
