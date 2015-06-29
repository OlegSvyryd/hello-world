package MVC.Model.DAO.WebStore.Impl;

import MVC.Model.DAO.WebStore.WebStoreDAO;
import MVC.Model.Entity.WebStore.WebStore;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 05.06.2015.
 */

@Repository
public class WebStoreDAOImpl implements WebStoreDAO{

    private SessionFactory sessionFactory;

    public WebStoreDAOImpl() {
    }

    @Autowired
    public WebStoreDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void insert(WebStore webStore) {
        sessionFactory.getCurrentSession().save(webStore);
        sessionFactory.getCurrentSession().getTransaction().commit();

    }

    @Override
    public void update(WebStore webStore) {
        sessionFactory.getCurrentSession().merge(webStore);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    public void delete(WebStore webStore) {
        sessionFactory.getCurrentSession().delete(webStore);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    public WebStore getById(int id) {
        return (WebStore)sessionFactory.getCurrentSession().get(WebStore.class, id);

    }

    @Override
    public WebStore getByURL(String url) {
        List<WebStore> webStores = new ArrayList<WebStore>();

        webStores = sessionFactory.getCurrentSession()
                .createQuery("from WebStore where url=?")
                .setParameter(0, url)
                .list();

        if (webStores.size() > 0 && webStores.size() < 2) {
            return webStores.get(0);
        } else {
            return null;
        }


    }

    @Override
    public List getAll(){
        return (List<Object[]>)sessionFactory.getCurrentSession().createSQLQuery("select distinct type_id AS typeId from web_store")
                .addScalar("typeId", new IntegerType())
                .list();

    }

    @Override
    public List<WebStore> getAll(int user_id) {
        return (List<WebStore>)sessionFactory.getCurrentSession().createQuery("from WebStore where user_Id =?")
                .setParameter(0, user_id)
                .list();

    }

    @Override
    public List<WebStore> getAllByType(int typeId) {
        return (List<WebStore>)sessionFactory.getCurrentSession().createQuery("from WebStore where type_Id =?")
                .setParameter(0, typeId)
                .list();
    }
}
