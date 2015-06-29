package MVC.Model.DAO.Users.UsersImpl;

import MVC.Model.DAO.Users.UserRoleDAO;
import MVC.Model.Entity.Users.UserRoles;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by oleg on 19.05.2015.
 */

@Repository
public class UserRoleDAOImpl implements UserRoleDAO{

    private SessionFactory sessionFactory;

    public UserRoleDAOImpl() {}

    @Autowired
    public UserRoleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(UserRoles role) {
        sessionFactory.getCurrentSession().save(role);
    }

    @Override
    public void delete(UserRoles role) {
        sessionFactory.getCurrentSession().save(role);
    }
}
