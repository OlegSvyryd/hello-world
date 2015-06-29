package MVC.Model.Service.WebStore.Impl;

import MVC.Model.DAO.WebStore.Impl.WebOrdersDAOImpl;
import MVC.Model.DAO.WebStore.WebOrdersDAO;
import MVC.Model.Entity.WebStore.WebOrders;
import MVC.Model.Service.WebStore.IWebOrdersService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by oleg on 10.06.2015.
 */

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class WebOrdersService implements IWebOrdersService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private WebOrdersDAO webOrdersDAO = new WebOrdersDAOImpl(sessionFactory);

    @Override
    public void insert(WebOrders webOrders) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        webOrdersDAO.insert(webOrders);
    }

    @Override
    public void update(WebOrders webOrders) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        webOrdersDAO.update(webOrders);
    }

    @Override
    public void delete(WebOrders webOrders) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        webOrdersDAO.delete(webOrders);
    }

    @Override
    public WebOrders getById(int id) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return webOrdersDAO.getById(id);
    }

    @Override
    public List<WebOrders> getAll(int web_store_id, String email) {
        return null;
    }

    @Override
    public List shoeOborot(int web_store_id, String beginDate, String endDate) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return webOrdersDAO.shoeOborot(web_store_id, beginDate, endDate);
    }

    @Override
    public List bookOborot(int web_store_id, String beginDate, String endDate) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return webOrdersDAO.bookOborot(web_store_id, beginDate, endDate);
    }

    @Override
    public List queueOrdersBook(int web_store_id, String email) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return webOrdersDAO.queueOrdersBook(web_store_id, email);
    }

    @Override
    public List queueOrdersShoe(int web_store_id, String email) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return webOrdersDAO.queueOrdersShoe(web_store_id, email);

    }
}
