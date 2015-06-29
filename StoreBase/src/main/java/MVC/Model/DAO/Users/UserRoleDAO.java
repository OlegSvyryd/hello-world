package MVC.Model.DAO.Users;

import MVC.Model.Entity.Users.UserRoles;

/**
 * Created by oleg on 19.05.2015.
 */
public interface UserRoleDAO {
        public void insert(UserRoles role);
        public void delete(UserRoles role);
}
