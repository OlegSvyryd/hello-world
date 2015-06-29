package MVC.Model.DAO.Company.Goods.GoodsDAOImpl;

import MVC.Model.DAO.Company.Goods.CompanyCatalogDAO;
import MVC.Model.Entity.Company.Goods.CompanyCatalog;
import MVC.Model.Entity.Store.Goods.StoreCatalog;
import com.googlecode.ehcache.annotations.TriggersRemove;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oleg on 26.05.2015.
 */

@Repository
public class CompanyCatalogDAOImpl implements CompanyCatalogDAO {

    private SessionFactory sessionFactory;

    public CompanyCatalogDAOImpl() {
    }

    @Autowired
    public CompanyCatalogDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @TriggersRemove(cacheName = "companiesGoodsCache", removeAll = true)
    @Override
    public void insert(CompanyCatalog companyCatalog) {
        sessionFactory.getCurrentSession().save(companyCatalog);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @TriggersRemove(cacheName = "companiesGoodsCache", removeAll = true)
    @Override
    public void update(CompanyCatalog companyCatalog) {
        sessionFactory.getCurrentSession().merge(companyCatalog);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @TriggersRemove(cacheName = "companiesGoodsCache", removeAll = true)
    @Override
    public void delete(CompanyCatalog companyCatalog) {
        sessionFactory.getCurrentSession().delete(companyCatalog);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Cacheable(value="companiesGoodsCache")
    @Override
    public CompanyCatalog getById(int id) {
        return (CompanyCatalog)sessionFactory.getCurrentSession().get(CompanyCatalog.class, id);
    }

    @Cacheable(value="companiesGoodsCache")
    @Override
    public List<CompanyCatalog> getAll(int company_id) {
        return (List<CompanyCatalog>)sessionFactory.getCurrentSession().createQuery("from CompanyCatalog u where u.company.id =?")
                .setParameter(0, company_id)
                .list();
    }

    @Cacheable(value="companiesGoodsCache")
    @Override
    public List<CompanyCatalog> getAll(int company_id, String email) {
        return (List<CompanyCatalog>)sessionFactory.getCurrentSession().createQuery("from CompanyCatalog u where u.company.id =? and u.company.users.email = ?")
                .setParameter(0, company_id)
                .setParameter(1, email)
                .list();
    }
}
