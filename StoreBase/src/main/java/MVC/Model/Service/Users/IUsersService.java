package MVC.Model.Service.Users;

import MVC.Model.Entity.Users.UserRoles;
import MVC.Model.Entity.Users.Users;

import java.util.List;

/**
 * Created by oleg on 19.05.2015.
 */
public interface IUsersService {
    public void insert(Users users, UserRoles userRoles);
    public void update(Users user);
    public void update(Users users, UserRoles userRoles);
    public void delete(Users user);
    public Users getById(int id);
    public Users getUserByEmail(String email);
    public Users getUserByPhone(String phone);
    public List<Users> getAll();
}
