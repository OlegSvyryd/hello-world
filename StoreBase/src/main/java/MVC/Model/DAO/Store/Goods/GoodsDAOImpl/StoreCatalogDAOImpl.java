package MVC.Model.DAO.Store.Goods.GoodsDAOImpl;

import MVC.Model.DAO.Store.Goods.StoreCatalogDAO;
import MVC.Model.Entity.Store.Goods.StoreCatalog;
import MVC.Model.Entity.Store.Store;
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
public class StoreCatalogDAOImpl implements StoreCatalogDAO {

    private SessionFactory sessionFactory;

    public StoreCatalogDAOImpl() {
    }

    @Autowired
    public StoreCatalogDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @TriggersRemove(cacheName = "storesGoodsCache", removeAll = true)
    @Override
    public void insert(StoreCatalog storeCatalog) {
        sessionFactory.getCurrentSession().save(storeCatalog);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @TriggersRemove(cacheName = "storesGoodsCache", removeAll = true)
    @Override
    public void update(StoreCatalog storeCatalog) {
        sessionFactory.getCurrentSession().merge(storeCatalog);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @TriggersRemove(cacheName = "storesGoodsCache", removeAll = true)
    @Override
    public void delete(StoreCatalog storeCatalog) {
        sessionFactory.getCurrentSession().delete(storeCatalog);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Cacheable(value="storesGoodsCache")
    @Override
    public StoreCatalog getById(int id) {
        return (StoreCatalog)sessionFactory.getCurrentSession().get(StoreCatalog.class, id);
    }

    @Cacheable(value="storesGoodsCache")
    @Override
    public List<StoreCatalog> getAll(int store_id, String email) {
        return (List<StoreCatalog>)sessionFactory.getCurrentSession().createQuery("from StoreCatalog u where u.store.id =? and u.store.users.email=?")
                .setParameter(0, store_id)
                .setParameter(1, email)
                .list();
    }
}
