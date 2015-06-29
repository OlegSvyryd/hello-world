package MVC.Model.DAO.WebStore.Impl;

import MVC.Model.DAO.WebStore.ShoeGoodsDAO;
import MVC.Model.Entity.WebStore.ShoeGoods;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oleg on 05.06.2015.
 */

@Repository
public class ShoeGoodsDAOImpl implements ShoeGoodsDAO{
    private SessionFactory sessionFactory;

    public ShoeGoodsDAOImpl() {
    }

    @Autowired
    public ShoeGoodsDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(ShoeGoods shoeGoods) {
        sessionFactory.getCurrentSession().save(shoeGoods);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    public void update(ShoeGoods shoeGoods) {
        sessionFactory.getCurrentSession().merge(shoeGoods);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    public void delete(ShoeGoods shoeGoods) {
        sessionFactory.getCurrentSession().delete(shoeGoods);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    public ShoeGoods getById(int id) {
        return (ShoeGoods)sessionFactory.getCurrentSession().get(ShoeGoods.class, id);

    }

    @Override
    public List<ShoeGoods> getAll(int webStoreId) {
        return (List<ShoeGoods>)sessionFactory.getCurrentSession().createQuery("from ShoeGoods where web_store_id =?")
                .setParameter(0, webStoreId)
                .list();

    }

    @Override
    public List<ShoeGoods> getAll(int webStoreId, String email) {
        return (List<ShoeGoods>)sessionFactory.getCurrentSession().createQuery("from ShoeGoods where web_store_id =? and webStore.users.email=?")
                .setParameter(0, webStoreId)
                .setParameter(1, email)
                .list();
    }
}
