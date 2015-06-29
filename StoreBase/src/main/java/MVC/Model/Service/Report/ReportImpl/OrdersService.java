package MVC.Model.Service.Report.ReportImpl;

import MVC.Model.DAO.Report.OrdersDAO;
import MVC.Model.DAO.Report.ReportDAOImpl.OrdersDAOImpl;
import MVC.Model.Entity.Report.Orders;
import MVC.Model.Service.Report.IOrdersService;
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
public class OrdersService implements IOrdersService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private OrdersDAO ordersDAO = new OrdersDAOImpl(sessionFactory);

    @Override
    public void insert(Orders orders) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        ordersDAO.insert(orders);
    }

    @Override
    public void update(Orders orders) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        ordersDAO.update(orders);
    }

    @Override
    public void delete(Orders orders) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        ordersDAO.delete(orders);
    }

    @Override
    public Orders getById(int id) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return ordersDAO.getById(id);
    }

    @Override
    public List<Orders> getAllByStore(int store_id, String email) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return ordersDAO.getAllByStore(store_id, email);
    }

    @Override
    public List<Orders> getAllByCompany(int company_id, String email) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return ordersDAO.getAllByCompany(company_id, email);
    }

    @Override
    public List<Orders> getAllConfirmedByCompany(int company_id, String email) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return ordersDAO.getAllConfirmedByCompany(company_id, email);
    }

    @Override
    public List oborot(int store_id, String beginDate, String endDate) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return ordersDAO.oborot(store_id, beginDate, endDate);
    }

    @Override
    public List pruhidTovary(int store_id, String beginDate, String endDate) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return ordersDAO.pruhidTovary(store_id, beginDate, endDate);
    }

    @Override
    public List prubytok(int store_id, String beginDate, String endDate) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return ordersDAO.prubytok(store_id, beginDate, endDate);
    }

    @Override
    public List rentabelnistj(int store_id, String beginDate, String endDate) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return ordersDAO.rentabelnistj(store_id, beginDate, endDate);
    }

    @Override
    public List cOborot(int company_id, String beginDate, String endDate) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return ordersDAO.cOborot(company_id, beginDate, endDate);
    }

    @Override
    public List cMinStatistic(int company_id, String beginDate, String endDate) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return ordersDAO.cMinStatistic(company_id, beginDate, endDate);
    }
}
