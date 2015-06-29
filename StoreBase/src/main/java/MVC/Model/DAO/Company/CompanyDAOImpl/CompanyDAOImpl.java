package MVC.Model.DAO.Company.CompanyDAOImpl;

import MVC.Model.DAO.Company.CompanyDAO;
import MVC.Model.Entity.Company.Company;
import com.googlecode.ehcache.annotations.TriggersRemove;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oleg on 23.05.2015.
 */

@Repository
public class CompanyDAOImpl implements CompanyDAO {

    private SessionFactory sessionFactory;

    public CompanyDAOImpl() {
    }

    @Autowired
    public CompanyDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @TriggersRemove(cacheName = "companiesCache", removeAll = true)
    @Override
    public void insert(Company company) {
        sessionFactory.getCurrentSession().save(company);
    }

    @TriggersRemove(cacheName = "companiesCache", removeAll = true)
    @Override
    public void update(Company company) {
        sessionFactory.getCurrentSession().merge(company);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @TriggersRemove(cacheName = "companiesCache", removeAll = true)
    @Override
    public void delete(Company company) {
        sessionFactory.getCurrentSession().delete(company);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Cacheable(value="companiesCache")
    @Override
    public Company getById(int id) {
        return (Company)sessionFactory.getCurrentSession().get(Company.class, id);
    }

    @Cacheable(value="companiesCache")
    @Override
    public List<Company> getAllByType(int type_id) {
        return (List<Company>)sessionFactory.getCurrentSession().createQuery("from Company u where u.companyType.id = ?")
                .setParameter(0, type_id)
                .list();
    }

    @Cacheable(value="companiesCache")
    @Override
    public List<Company> getAll(int directorId) {
        return (List<Company>)sessionFactory.getCurrentSession().createQuery("from Company where director_id = ?")
                .setParameter(0, directorId)
                .list();
    }
}
