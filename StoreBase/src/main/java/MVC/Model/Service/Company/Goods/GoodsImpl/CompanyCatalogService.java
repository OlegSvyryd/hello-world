package MVC.Model.Service.Company.Goods.GoodsImpl;

import MVC.Model.DAO.Company.Goods.CompanyCatalogDAO;
import MVC.Model.DAO.Company.Goods.GoodsDAOImpl.CompanyCatalogDAOImpl;
import MVC.Model.Entity.Company.Goods.CompanyCatalog;
import MVC.Model.Service.Company.Goods.ICompanyCatalogService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by oleg on 26.05.2015.
 */

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class CompanyCatalogService implements ICompanyCatalogService{

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CompanyCatalogDAO companyCatalogDAO = new CompanyCatalogDAOImpl(sessionFactory);

    @Override
    public void insert(CompanyCatalog companyCatalog) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        companyCatalogDAO.insert(companyCatalog);
    }

    @Override
    public void update(CompanyCatalog companyCatalog) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        companyCatalogDAO.update(companyCatalog);
    }

    @Override
    public void delete(CompanyCatalog companyCatalog) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        companyCatalogDAO.delete(companyCatalog);
    }

    @Override
    public CompanyCatalog getById(int id) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return companyCatalogDAO.getById(id);
    }

    @Override
    public List<CompanyCatalog> getAll(int company_id) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return companyCatalogDAO.getAll(company_id);
    }

    @Override
    public List<CompanyCatalog> getAll(int company_id, String email) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return companyCatalogDAO.getAll(company_id, email);
    }
}
