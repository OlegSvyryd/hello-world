package MVC.Model.DAO.Store.StoreDAOImpl;

import MVC.Model.DAO.Store.StoreDAO;
import MVC.Model.Entity.Store.Store;
import com.googlecode.ehcache.annotations.TriggersRemove;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oleg on 22.05.2015.
 */

@Repository
public class StoreDAOImpl implements StoreDAO {

    private SessionFactory sessionFactory;

    public StoreDAOImpl() {
    }

    @Autowired
    public StoreDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @TriggersRemove(cacheName = "storesCache", removeAll = true)
    @Override
    public void insert(Store store) {
        sessionFactory.getCurrentSession().save(store);
    }

    @TriggersRemove(cacheName = "storesCache", removeAll = true)
    @Override
    public void update(Store store) {
        sessionFactory.getCurrentSession().merge(store);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @TriggersRemove(cacheName = "storesCache", removeAll = true)
    @Override
    public void delete(Store store) {
        sessionFactory.getCurrentSession().delete(store);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Cacheable(value="storesCache")
    @Override
    public Store getById(int id) {
        return (Store)sessionFactory.getCurrentSession().get(Store.class, id);
    }

    @Cacheable(value="storesCache")
    @Override
    public List<Store> getAllByType(int type_id) {
        return (List<Store>)sessionFactory.getCurrentSession().createQuery("from Store u where u.storeType.id =?")
                .setParameter(0, type_id)
                .list();
    }

    @Cacheable(value="storesCache")
    @Override
    public List<Store> getAll(int directorId) {
        return (List<Store>)sessionFactory.getCurrentSession().createQuery("from Store where director_id =?")
                .setParameter(0, directorId)
                .list();
    }

    @Cacheable(value="storesCache")
    @Override
    public List<Store> getAllWithoutThisGoods(int user_id, Integer goods_id) {
        return (List<Store>)sessionFactory.getCurrentSession()
                .createQuery("from Store u where (u.users.id = ?) and " +
                        " not exists(" +
                        "from StoreCatalog u1 where " +
                        " (u1.store.id = u.id) and (u1.companyGoodsId = ?) " +
                        ")")
                .setParameter(0, user_id)
                .setParameter(1, goods_id)
                .list();

    }
}
