package MVC.Model.DAO.WebStore.Impl;

import MVC.Model.DAO.WebStore.BookGoodsDAO;
import MVC.Model.Entity.WebStore.BookGoods;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oleg on 05.06.2015.
 */

@Repository
public class BookGoodsDAOImpl implements BookGoodsDAO{
    private SessionFactory sessionFactory;

    public BookGoodsDAOImpl() {
    }

    @Autowired
    public BookGoodsDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void insert(BookGoods bookGoods) {
        sessionFactory.getCurrentSession().save(bookGoods);
        sessionFactory.getCurrentSession().getTransaction().commit();

    }

    @Override
    public void update(BookGoods bookGoods) {
        sessionFactory.getCurrentSession().merge(bookGoods);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    public void delete(BookGoods bookGoods) {
        sessionFactory.getCurrentSession().delete(bookGoods);
        sessionFactory.getCurrentSession().getTransaction().commit();

    }

    @Override
    public BookGoods getById(int id) {
        return (BookGoods)sessionFactory.getCurrentSession().get(BookGoods.class, id);

    }

    @Override
    public List<BookGoods> getAll(int webStoreId) {
        return (List<BookGoods>)sessionFactory.getCurrentSession().createQuery("from BookGoods where web_store_id =?")
                .setParameter(0, webStoreId)
                .list();

    }

    @Override
    public List<BookGoods> getAll(int webStoreId, String email) {
        return (List<BookGoods>)sessionFactory.getCurrentSession().createQuery("from BookGoods where web_store_id =? and webStore.users.email=?")
                .setParameter(0, webStoreId)
                .setParameter(1, email)
                .list();

    }
}

