package MVC.Model.DAO.Store.StoreDAOImpl;

import MVC.Model.DAO.Store.StoreTypeDAO;
import MVC.Model.Entity.Store.StoreType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oleg on 23.05.2015.
 */

@Repository
public class StoreTypeDAOImpl implements StoreTypeDAO {

    private SessionFactory sessionFactory;

    public StoreTypeDAOImpl() {
    }

    @Autowired
    public StoreTypeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(StoreType storeType) {
        sessionFactory.getCurrentSession().save(storeType);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    public void delete(StoreType storeType) {
        sessionFactory.getCurrentSession().delete(storeType);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    public StoreType getById(int id) {
        return (StoreType)sessionFactory.getCurrentSession().get(StoreType.class, id);
    }

    @Override
    public List<StoreType> getAll() {
        return (List<StoreType>)sessionFactory.getCurrentSession().createCriteria(StoreType.class).list();
    }
}
