package MVC.Model.Service.Users.UsersImpl;

import MVC.Model.DAO.Users.UserDAO;
import MVC.Model.DAO.Users.UserRoleDAO;
import MVC.Model.DAO.Users.UsersImpl.UserDAOImpl;
import MVC.Model.DAO.Users.UsersImpl.UserRoleDAOImpl;
import MVC.Model.Entity.Users.UserRoles;
import MVC.Model.Entity.Users.Users;
import MVC.Model.Service.Users.IUsersService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.transaction.TransactionManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by oleg on 19.05.2015.
 */

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class UsersService implements IUsersService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UserDAO userDAO = new UserDAOImpl(sessionFactory);

    @Autowired
    private UserRoleDAO userRoleDAO = new UserRoleDAOImpl(sessionFactory);

    @Override
    public void insert(final Users users, final UserRoles userRoles) {
        userDAO.insert(users);
        userRoleDAO.insert(userRoles);
    }

    @Override
    public void update(Users user) {
        System.out.println("here serr");
        userDAO.update(user);
    }

    @Override
    public void update(Users users, UserRoles userRoles) {
        userDAO.update(users);
        userRoleDAO.insert(userRoles);
    }

    @Override
    public void delete(Users user) {
        userDAO.delete(user);
    }

    @Override
    public Users getById(int id) {
        return userDAO.getById(id);
    }

    @Override
    public Users getUserByEmail(String email) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().beginTransaction();
        return userDAO.getByEmail(email);
    }

    @Override
    public Users getUserByPhone(String phone) {
        if(!sessionFactory.getCurrentSession().getTransaction().isActive())
            sessionFactory.getCurrentSession().beginTransaction();
        return userDAO.getByPhone(phone);
    }


    @Override
    public List<Users> getAll() {
        return userDAO.getAll();
    }
}
