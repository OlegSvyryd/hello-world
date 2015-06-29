package MVC.Model.DAO.Company.Goods.GoodsDAOImpl;

import MVC.Model.DAO.Company.Goods.GoodsTypeDAO;
import MVC.Model.Entity.Company.Goods.CompanyCatalog;
import MVC.Model.Entity.Company.Goods.GoodsType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oleg on 26.05.2015.
 */

@Repository
public class GoodsTypeDAOImpl implements GoodsTypeDAO {

    private SessionFactory sessionFactory;

    public GoodsTypeDAOImpl() {
    }

    @Autowired
    public GoodsTypeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(GoodsType goodsType) {
        sessionFactory.getCurrentSession().save(goodsType);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    public void update(GoodsType goodsType) {
        sessionFactory.getCurrentSession().merge(goodsType);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    public void delete(GoodsType goodsType) {
        sessionFactory.getCurrentSession().save(goodsType);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    public GoodsType getById(int id) {
        return (GoodsType)sessionFactory.getCurrentSession().get(GoodsType.class, id);
    }

    @Override
    public List<GoodsType> getAll() {
        return (List<GoodsType>) sessionFactory.getCurrentSession().
                createQuery("from GoodsType where parentId is not null").list();
    }
}
