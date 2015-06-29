package MVC.Model.Service.Store.StoreImpl;

import MVC.Model.DAO.Store.StoreDAO;
import MVC.Model.DAO.Store.StoreDAOImpl.StoreDAOImpl;
import MVC.Model.Entity.Store.Store;
import MVC.Model.Service.Store.IStoreService;
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
public class StoreService implements IStoreService{

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private StoreDAO storeDAO = new StoreDAOImpl(sessionFactory);

    @Override
    public void insert(Store store) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        storeDAO.insert(store);
    }

    @Override
    public void update(Store store) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        storeDAO.update(store);
    }

    @Override
    public void delete(Store store) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        storeDAO.delete(store);
    }

    @Override
    public Store getById(int id) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return storeDAO.getById(id);
    }

    @Override
    public List<Store> getAllByType(int type_id) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return storeDAO.getAllByType(type_id);
    }

    @Override
    public List<Store> getAll(int directorId) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return storeDAO.getAll(directorId);
    }

    @Override
    public List<Store> getAllWithoutThisGoods(int user_id, Integer goods_id) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return storeDAO.getAllWithoutThisGoods(user_id, goods_id);
    }
}
