package MVC.Model.Service.Store.StoreImpl;

import MVC.Model.DAO.Store.StoreDAO;
import MVC.Model.DAO.Store.StoreDAOImpl.StoreDAOImpl;
import MVC.Model.DAO.Store.StoreDAOImpl.StoreTypeDAOImpl;
import MVC.Model.DAO.Store.StoreTypeDAO;
import MVC.Model.Entity.Store.StoreType;
import MVC.Model.Service.Store.IStoreTypeService;
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
public class StoreTypeService implements IStoreTypeService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private StoreTypeDAO storeTypeDAO = new StoreTypeDAOImpl(sessionFactory);

    @Override
    public void insert(StoreType storeType) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        storeTypeDAO.insert(storeType);
    }

    @Override
    public void delete(StoreType storeType) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        storeTypeDAO.delete(storeType);
    }

    @Override
    public StoreType getById(int id) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return storeTypeDAO.getById(id);
    }

    @Override
    public List<StoreType> getAll() {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return storeTypeDAO.getAll();
    }
}
