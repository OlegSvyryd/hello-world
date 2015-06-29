package MVC.Model.Service.WebStore.Impl;

import MVC.Model.DAO.WebStore.Impl.WebStoreDAOImpl;
import MVC.Model.DAO.WebStore.WebStoreDAO;
import MVC.Model.Entity.WebStore.WebStore;
import MVC.Model.Service.WebStore.IWebStoreService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by oleg on 05.06.2015.
 */

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class WebStoreService implements IWebStoreService{

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private WebStoreDAO webStoreDAO = new WebStoreDAOImpl(sessionFactory);

    @Override
    public void insert(WebStore webStore) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        webStoreDAO.insert(webStore);
    }

    @Override
    public void update(WebStore webStore) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        webStoreDAO.update(webStore);

    }

    @Override
    public void delete(WebStore webStore) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        webStoreDAO.delete(webStore);

    }

    @Override
    public WebStore getById(int id) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return webStoreDAO.getById(id);

    }

    @Override
    public WebStore getByURL(String url) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return webStoreDAO.getByURL(url);

    }

    @Override
    public List getAll() {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return webStoreDAO.getAll();

    }

    @Override
    public List<WebStore> getAll(int user_id) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return webStoreDAO.getAll(user_id);

    }

    @Override
    public List<WebStore> getAllByType(int typeId) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return webStoreDAO.getAllByType(typeId);

    }
}
