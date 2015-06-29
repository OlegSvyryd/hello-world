package MVC.Model.DAO.Users.UsersImpl;

import MVC.Model.DAO.Users.UserDAO;
import MVC.Model.Entity.Users.Users;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 19.05.2015.
 */

@Repository
public class UserDAOImpl implements UserDAO{

    private SessionFactory sessionFactory;

    public UserDAOImpl() {}

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(Users user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void update(Users user) {
        sessionFactory.getCurrentSession().merge(user);
        sessionFactory.getCurrentSession().getTransaction().commit();
        System.out.println("here daao");
    }

    @Override
    public void delete(Users user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public Users getById(int id) {
        return (Users) sessionFactory.getCurrentSession().get(Users.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Users getByEmail(String email) {

        List<Users> users = new ArrayList<Users>();

        users = sessionFactory.getCurrentSession()
                .createQuery("from Users where email=?")
                .setParameter(0, email)
                .list();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Users getByPhone(String phone) {

        List<Users> users = new ArrayList<Users>();

        users = sessionFactory.getCurrentSession()
                .createQuery("from Users where phone=?")
                .setParameter(0, phone)
                .list();

        System.out.println(users.size());
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Users> getAll() {
        return (List<Users>) sessionFactory.getCurrentSession().createCriteria(Users.class).list();
    }
}
