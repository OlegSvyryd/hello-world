package MVC.Model.Service.WebStore.Impl;

import MVC.Model.DAO.WebStore.BookGoodsDAO;
import MVC.Model.DAO.WebStore.Impl.BookGoodsDAOImpl;
import MVC.Model.Entity.WebStore.BookGoods;
import MVC.Model.Service.WebStore.IBookGoodsService;
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
public class BookGoodsService implements IBookGoodsService{

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private BookGoodsDAO bookGoodsDAO = new BookGoodsDAOImpl(sessionFactory);


    @Override
    public void insert(BookGoods bookGoods) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        bookGoodsDAO.insert(bookGoods);

    }

    @Override
    public void update(BookGoods bookGoods) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        bookGoodsDAO.update(bookGoods);

    }

    @Override
    public void delete(BookGoods bookGoods) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        bookGoodsDAO.delete(bookGoods);

    }

    @Override
    public BookGoods getById(int id) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return bookGoodsDAO.getById(id);

    }

    @Override
    public List<BookGoods> getAll(int webStoreId) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return bookGoodsDAO.getAll(webStoreId);

    }

    @Override
    public List<BookGoods> getAll(int webStoreId, String email) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().getTransaction().begin();
        return bookGoodsDAO.getAll(webStoreId, email);

    }
}
