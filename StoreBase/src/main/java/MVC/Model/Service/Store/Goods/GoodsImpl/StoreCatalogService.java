package MVC.Model.Service.Store.Goods.GoodsImpl;

import MVC.Model.DAO.Store.Goods.GoodsDAOImpl.StoreCatalogDAOImpl;
import MVC.Model.DAO.Store.Goods.StoreCatalogDAO;
import MVC.Model.Entity.Store.Goods.StoreCatalog;
import MVC.Model.Service.Store.Goods.IStoreCatalogService;
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
public class StoreCatalogService implements IStoreCatalogService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private StoreCatalogDAO storeCatalogDAO = new StoreCatalogDAOImpl(sessionFactory);

    @Override
    public void insert(StoreCatalog storeCatalog) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        storeCatalogDAO.insert(storeCatalog);
    }

    @Override
    public void update(StoreCatalog storeCatalog) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        storeCatalogDAO.update(storeCatalog);
    }

    @Override
    public void delete(StoreCatalog storeCatalog) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        storeCatalogDAO.delete(storeCatalog);
    }

    @Override
    public StoreCatalog getById(int id) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return storeCatalogDAO.getById(id);
    }

    @Override
    public List<StoreCatalog> getAll(int store_id, String email) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return storeCatalogDAO.getAll(store_id, email);
    }
}
