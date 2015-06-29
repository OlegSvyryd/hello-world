package MVC.Model.Service.WebStore.Impl;

import MVC.Model.DAO.WebStore.Impl.ShoeGoodsDAOImpl;
import MVC.Model.DAO.WebStore.ShoeGoodsDAO;
import MVC.Model.Entity.WebStore.ShoeGoods;
import MVC.Model.Service.WebStore.IShoeGoodsService;
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
public class ShoeGoodsService implements IShoeGoodsService{

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ShoeGoodsDAO shoeGoodsDAO = new ShoeGoodsDAOImpl(sessionFactory);


    @Override
    public void insert(ShoeGoods shoeGoods) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        shoeGoodsDAO.insert(shoeGoods);

    }

    @Override
    public void update(ShoeGoods shoeGoods) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        shoeGoodsDAO.update(shoeGoods);
    }

    @Override
    public void delete(ShoeGoods shoeGoods) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        shoeGoodsDAO.delete(shoeGoods);

    }

    @Override
    public ShoeGoods getById(int id) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return shoeGoodsDAO.getById(id);

    }

    @Override
    public List<ShoeGoods> getAll(int webStoreId) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return shoeGoodsDAO.getAll(webStoreId);

    }

    @Override
    public List<ShoeGoods> getAll(int webStoreId, String email) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return shoeGoodsDAO.getAll(webStoreId, email);

    }
}
