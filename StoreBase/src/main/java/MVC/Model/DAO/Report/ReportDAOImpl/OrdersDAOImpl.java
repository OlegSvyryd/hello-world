package MVC.Model.DAO.Report.ReportDAOImpl;

import MVC.Model.DAO.Report.OrdersDAO;
import MVC.Model.Entity.Report.Orders;
import org.hibernate.SessionFactory;
import org.hibernate.type.DoubleType;
import org.hibernate.type.FloatType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oleg on 26.05.2015.
 */

@Repository
public class OrdersDAOImpl implements OrdersDAO {

    private SessionFactory sessionFactory;

    public OrdersDAOImpl() {
    }

    @Autowired
    public OrdersDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(Orders orders) {
        sessionFactory.getCurrentSession().save(orders);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    public void update(Orders orders) {
        sessionFactory.getCurrentSession().merge(orders);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    public void delete(Orders orders) {
        sessionFactory.getCurrentSession().delete(orders);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    public Orders getById(int id) {
        return (Orders)sessionFactory.getCurrentSession().get(Orders.class, id);
    }

    @Override
    public List<Orders> getAllByStore(int store_id, String email) {
        return (List<Orders>)sessionFactory.getCurrentSession()
                .createQuery("from Orders u where u.storeCatalog.store.id = ? and u.paid = false and u.visible = true and u.step = 0 and u.storeCatalog.store.users.email = ?")
                .setParameter(0, store_id)
                .setParameter(1, email)
                .list();
    }

    @Override
    public List<Orders> getAllByCompany(int company_id, String email) {
        return (List<Orders>)sessionFactory.getCurrentSession()
                .createQuery("from Orders u where u.companyCatalog.company.id = ? and u.confirm = 0 and u.step = 0 and u.companyCatalog.company.users.email = ?")
                .setParameter(0, company_id)
                .setParameter(1, email)
                .list();
    }

    @Override
    public List<Orders> getAllConfirmedByCompany(int company_id, String email) {
        return (List<Orders>)sessionFactory.getCurrentSession()
                .createQuery("from Orders u where u.companyCatalog.company.id = ? and u.confirm = 1 and u.paid = false and u.step = 0 and u.companyCatalog.company.users.email = ?")
                .setParameter(0, company_id)
                .setParameter(1, email)
                .list();
    }

    @Override
    public List oborot(int store_id, String beginDate, String endDate) {
        return (List<Object[]>)sessionFactory.getCurrentSession()
                .createSQLQuery(
                        "SELECT t1.name AS goodsName, " +
                                "t1.company_name AS companyName, " +
                                "SUM(t2.amount) AS amountSum, " +
                                "t1.price AS storePriceOneGoods, " +
                                "(AVG(t2.store_price) * SUM(t2.amount)) AS summ " +
                                "FROM store_catalog t1 " +
                                "INNER JOIN orders t2 ON (t1.id = t2.catalog_store_id) " +
                                "WHERE (t2.date BETWEEN '" + beginDate + "' AND '" + endDate + "') " +
                                "AND (t1.store_id = " + store_id + ") " +
                                "AND (t2.step = 1)" +
                                "GROUP BY t1.name, t1.price, t1.company_name, t2.catalog_store_id")
                .addScalar("goodsName", new StringType())
                .addScalar("companyName", new StringType())
                .addScalar("amountSum", new IntegerType())
                .addScalar("storePriceOneGoods", new StringType())
                .addScalar("summ", new FloatType())
                .list();
    }

    @Override
    public List pruhidTovary(int store_id, String beginDate, String endDate) {
        return (List<Object[]>)sessionFactory.getCurrentSession()
                .createSQLQuery(
                        "SELECT t1.name AS goodsName, " +
                                "t1.company_name AS companyName, " +
                                "SUM(t2.amount) AS amountSum, " +
                                "AVG(t2.order_price) AS originalPriceOneGoods, " +
                                "(AVG(t2.order_price) * SUM(t2.amount)) AS summ " +
                                "FROM store_catalog t1 " +
                                "INNER JOIN orders t2 ON (t1.id = t2.catalog_store_id) " +
                                "WHERE (t2.date BETWEEN '" + beginDate + "' AND '" + endDate + "') " +
                                "AND (t1.store_id = " + store_id + ") " +
                                "AND (t2.step = 0) AND (t2.paid IS TRUE) " +
                                "GROUP BY t1.name, t1.price, t1.company_name, t2.catalog_company_id")
                .addScalar("goodsName", new StringType())
                .addScalar("companyName", new StringType())
                .addScalar("amountSum", new IntegerType())
                .addScalar("originalPriceOneGoods", new FloatType())
                .addScalar("summ", new FloatType())
                .list();
    }

    @Override
    public List prubytok(int store_id, String beginDate, String endDate) {
        return (List<Object[]>)sessionFactory.getCurrentSession()
                .createSQLQuery(
                        "SELECT t1.name AS goodsName, " +
                                "t1.company_name AS companyName, " +
                                "SUM(t2.amount) AS amountSum, " +
                                "((AVG(t2.store_price)-AVG(t2.order_price)) * SUM(t2.amount))  AS summ " +
                                "FROM store_catalog t1 " +
                                "INNER JOIN orders t2 ON (t1.id = t2.catalog_store_id) " +
                                "WHERE (t2.date BETWEEN '" + beginDate + "' AND '" + endDate + "') " +
                                "AND (t1.store_id = " + store_id + ") " +
                                "AND (t2.step = 1) " +
                                "GROUP BY t1.name, t1.company_name, t2.catalog_store_id")
                .addScalar("goodsName", new StringType())
                .addScalar("companyName", new StringType())
                .addScalar("amountSum", new IntegerType())
                .addScalar("summ", new FloatType())
                .list();
    }

    @Override
    public List rentabelnistj(int store_id, String beginDate, String endDate) {
        return (List<Object[]>)sessionFactory.getCurrentSession()
                .createSQLQuery(
                        "SELECT t1.name AS goodsName, " +
                                "t1.company_name AS companyName, " +
                                "((AVG(t2.store_price) * SUM(t2.amount)) / (AVG(t2.order_price) * SUM(t2.amount))) AS summ " +
                                "FROM store_catalog t1 " +
                                "INNER JOIN orders t2 ON (t1.id = t2.catalog_store_id) " +
                                "WHERE (t2.date BETWEEN '" + beginDate + "' AND '" + endDate + "') " +
                                "AND (t1.store_id = " + store_id + ") " +
                                "AND (t2.step = 1) " +
                                "GROUP BY t1.name, t1.company_name, t2.catalog_store_id")
                .addScalar("goodsName", new StringType())
                .addScalar("companyName", new StringType())
                .addScalar("summ", new FloatType())
                .list();
    }

    @Override
    public List cOborot(int company_id, String beginDate, String endDate) {
        return (List<Object[]>)sessionFactory.getCurrentSession()
                .createSQLQuery(
                        "SELECT t1.name AS goodsName, " +
                                "SUM(t2.amount) AS amountSum, " +
                                "t1.price AS companyPriceOneGoods, " +
                                "(AVG(t2.order_price) * SUM(t2.amount)) AS summ " +
                                "FROM company_catalog t1 " +
                                "INNER JOIN orders t2 ON (t1.id = t2.catalog_company_id) " +
                                "WHERE (t2.date BETWEEN '" + beginDate + "' AND '" + endDate + "') " +
                                "AND (t1.company_id = " + company_id + ") " +
                                "AND (t2.step = 0) AND (t2.paid IS TRUE) " +
                                "GROUP BY t1.name, t1.price, t2.catalog_company_id")
                .addScalar("goodsName", new StringType())
                .addScalar("amountSum", new IntegerType())
                .addScalar("companyPriceOneGoods", new FloatType())
                .addScalar("summ", new FloatType())
                .list();
    }

    @Override
    public List cMinStatistic(int company_id, String beginDate, String endDate) {
        return (List<Object[]>)sessionFactory.getCurrentSession()
                .createSQLQuery(
                        "select count(t3.id) as amount_orders, t4.name as store_name, t2.name as goods_name, SUM(orders.amount) as amount_sum, (AVG(orders.order_price) * SUM(orders.amount)) as summ " +
                                "from orders " +
                                "inner join store_catalog t3 ON " +
                                "(t3.id = orders.catalog_store_id) " +
                                "right join store t4 ON " +
                                "(t3.store_id = t4.id) " +
                                "inner join company_catalog t2 ON " +
                                "(t2.id = orders.catalog_company_id) " +
                                "where (orders.date BETWEEN '" + beginDate + "' AND '" + endDate + "') and " +
                                "t2.company_id = " + company_id + " and " +
                                "catalog_store_id is not null and " +
                                "catalog_company_id is not null and " +
                                "step = 0 AND (paid IS TRUE)" +
                                "group by t4.name, t2.name")
                .addScalar("amount_orders", new IntegerType())
                .addScalar("store_name", new StringType())
                .addScalar("goods_name", new StringType())
                .addScalar("amount_sum", new IntegerType())
                .addScalar("summ", new FloatType())
                .list();

    }
}
