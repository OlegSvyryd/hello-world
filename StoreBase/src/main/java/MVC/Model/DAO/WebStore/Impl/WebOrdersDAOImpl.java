package MVC.Model.DAO.WebStore.Impl;

import MVC.Model.DAO.WebStore.WebOrdersDAO;
import MVC.Model.Entity.WebStore.WebOrders;
import org.hibernate.SessionFactory;
import org.hibernate.type.FloatType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oleg on 10.06.2015.
 */

@Repository
public class WebOrdersDAOImpl implements WebOrdersDAO {

    private SessionFactory sessionFactory;

    public WebOrdersDAOImpl() {
    }

    @Autowired
    public WebOrdersDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(WebOrders webOrders) {
        sessionFactory.getCurrentSession().save(webOrders);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    public void update(WebOrders webOrders) {
        sessionFactory.getCurrentSession().merge(webOrders);
        sessionFactory.getCurrentSession().getTransaction().commit();

    }

    @Override
    public void delete(WebOrders webOrders) {
        sessionFactory.getCurrentSession().delete(webOrders);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    public WebOrders getById(int id) {
        return (WebOrders)sessionFactory.getCurrentSession().get(WebOrders.class, id);

    }

    @Override
    public List<WebOrders> getAll(int web_store_id, String email) {
/*
        return (List<WebOrders>)sessionFactory.getCurrentSession()
                .createQuery("from WebOrders u where u.storeCatalog.store.id = ? and u.paid = false and u.visible = true and u.step = 0 and u.storeCatalog.store.users.email = ?")
                .setParameter(0, store_id)
                .setParameter(1, email)
                .list();
*/
        return null;
    }

    @Override
    public List shoeOborot(int web_store_id, String beginDate, String endDate) {
        return (List<Object[]>)sessionFactory.getCurrentSession()
                .createSQLQuery(
                        "SELECT t3.name AS webStoreName, " +
                                "t1.name AS goodsName, " +
                                "SUM(t2.amount) AS amountSum, " +
                                "t1.price AS storePriceOneGoods, " +
                                "(AVG(t2.price) * SUM(t2.amount)) AS summ " +
                                "FROM shoe_goods t1 " +
                                "INNER JOIN web_orders t2 ON (t1.id = t2.shoe_goods_id) " +
                                "INNER JOIN web_store t3 ON (t1.web_store_id = t3.id) " +
                                "WHERE (t2.date BETWEEN '" + beginDate + "' AND '" + endDate + "') " +
                                "AND (t3.id = " + web_store_id + ") " +
                                "GROUP BY t3.name, t1.name, t1.price, t2.shoe_goods_id")
                .addScalar("webStoreName", new StringType())
                .addScalar("goodsName", new StringType())
                .addScalar("amountSum", new IntegerType())
                .addScalar("storePriceOneGoods", new StringType())
                .addScalar("summ", new FloatType())
                .list();
    }

    @Override
    public List bookOborot(int web_store_id, String beginDate, String endDate) {
        return (List<Object[]>)sessionFactory.getCurrentSession()
                .createSQLQuery(
                        "SELECT t3.name AS webStoreName, " +
                                "t1.name AS goodsName, " +
                                "SUM(t2.amount) AS amountSum, " +
                                "t1.price AS storePriceOneGoods, " +
                                "(AVG(t2.price) * SUM(t2.amount)) AS summ " +
                                "FROM book_goods t1 " +
                                "INNER JOIN web_orders t2 ON (t1.id = t2.book_goods_id) " +
                                "INNER JOIN web_store t3 ON (t1.web_store_id = t3.id) " +
                                "WHERE (t2.date BETWEEN '" + beginDate + "' AND '" + endDate + "') " +
                                "AND (t3.id = " + web_store_id + ") " +
                                "GROUP BY t3.name, t1.name, t1.price, t2.book_goods_id")
                .addScalar("webStoreName", new StringType())
                .addScalar("goodsName", new StringType())
                .addScalar("amountSum", new IntegerType())
                .addScalar("storePriceOneGoods", new StringType())
                .addScalar("summ", new FloatType())
                .list();

    }

    @Override
    public List queueOrdersBook(int web_store_id, String email) {
        return (List<Object[]>)sessionFactory.getCurrentSession()
                .createSQLQuery(
                        "SELECT t1.date AS dateOperation, " +
                                "t1.name AS clientName, " +
                                "t1.phone AS clientPhone, " +
                                "t1.email AS clientEmail, " +
                                "t2.name AS goodsName, " +
                                "t1.amount AS amountOrder, " +
                                "(t1.price * t1.amount) AS price, " +
                                "t1.id AS id, " +
                                "t1.confirm AS confirm " +
                                "FROM web_orders t1 " +
                                "INNER JOIN book_goods t2 ON (t1.book_goods_id = t2.id) " +
                                "INNER JOIN web_store t3 ON (t2.web_store_id = t3.id) " +
                                "INNER JOIN users t4 ON (t4.id = t3.user_id) " +
                                "WHERE (t2.web_store_id = " + web_store_id + ") " +
                                "AND (t4.email = '" + email + "') " +
                                "AND (t1.confirm != -1) " +
                                "AND (t1.paid = false) " +
                                "GROUP BY t1.date, t1.name, t1.phone, t1.email, t2.name, t1.amount, t1.id, t1.confirm")
                .addScalar("dateOperation", new TimestampType())
                .addScalar("clientName", new StringType())
                .addScalar("clientPhone", new StringType())
                .addScalar("clientEmail", new StringType())
                .addScalar("goodsName", new StringType())
                .addScalar("amountOrder", new IntegerType())
                .addScalar("price", new FloatType())
                .addScalar("id", new IntegerType())
                .addScalar("confirm", new IntegerType())
                .list();
    }

    @Override
    public List queueOrdersShoe(int web_store_id, String email) {
        return (List<Object[]>)sessionFactory.getCurrentSession()
                .createSQLQuery(
                        "SELECT t1.date AS dateOperation, " +
                                "t1.name AS clientName, " +
                                "t1.phone AS clientPhone, " +
                                "t1.email AS clientEmail, " +
                                "t2.name AS goodsName, " +
                                "t1.amount AS amountOrder, " +
                                "(t1.price * t1.amount) AS price, " +
                                "t1.id AS id, " +
                                "t1.confirm AS confirm " +
                                "FROM web_orders t1 " +
                                "INNER JOIN shoe_goods t2 ON (t1.shoe_goods_id = t2.id) " +
                                "INNER JOIN web_store t3 ON (t2.web_store_id = t3.id) " +
                                "INNER JOIN users t4 ON (t4.id = t3.user_id) " +
                                "WHERE (t2.web_store_id = " + web_store_id + ") " +
                                "AND (t4.email = '" + email + "') " +
                                "AND (t1.confirm != -1) " +
                                "AND (t1.paid = false) " +
                                "GROUP BY t1.date, t1.name, t1.phone, t1.email, t2.name, t1.amount, t1.id, t1.confirm")
                .addScalar("dateOperation", new TimestampType())
                .addScalar("clientName", new StringType())
                .addScalar("clientPhone", new StringType())
                .addScalar("clientEmail", new StringType())
                .addScalar("goodsName", new StringType())
                .addScalar("amountOrder", new IntegerType())
                .addScalar("price", new FloatType())
                .addScalar("id", new IntegerType())
                .addScalar("confirm", new IntegerType())
                .list();
    }
}
