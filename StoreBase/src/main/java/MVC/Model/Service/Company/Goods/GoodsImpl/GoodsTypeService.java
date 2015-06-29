package MVC.Model.Service.Company.Goods.GoodsImpl;

import MVC.Model.DAO.Company.Goods.GoodsDAOImpl.GoodsTypeDAOImpl;
import MVC.Model.DAO.Company.Goods.GoodsTypeDAO;
import MVC.Model.Entity.Company.Goods.GoodsType;
import MVC.Model.Service.Company.Goods.IGoodsTypeService;
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
public class GoodsTypeService implements IGoodsTypeService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private GoodsTypeDAO goodsTypeDAO = new GoodsTypeDAOImpl(sessionFactory);

    @Override
    public void insert(GoodsType goodsType) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        goodsTypeDAO.insert(goodsType);
    }

    @Override
    public void update(GoodsType goodsType) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        goodsTypeDAO.update(goodsType);
    }

    @Override
    public void delete(GoodsType goodsType) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        goodsTypeDAO.delete(goodsType);
    }

    @Override
    public GoodsType getById(int id) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return goodsTypeDAO.getById(id);
    }

    @Override
    public List<GoodsType> getAll() {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return goodsTypeDAO.getAll();
    }
}
