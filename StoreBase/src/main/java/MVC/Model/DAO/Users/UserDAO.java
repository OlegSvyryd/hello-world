package MVC.Model.DAO.Users;

import MVC.Model.Entity.Users.Users;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by oleg on 19.05.2015.
 */
public interface UserDAO {
    public void insert(Users user);
    public void update(Users user);
    public void delete(Users user);
    public Users getById(int id);
    public Users getByEmail(String email);
    public Users getByPhone(String phone);
    public List<Users> getAll();
}
