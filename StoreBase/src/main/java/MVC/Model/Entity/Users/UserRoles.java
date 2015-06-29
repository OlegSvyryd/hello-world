package MVC.Model.Entity.Users;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by oleg on 19.05.2015.
 */

@Entity
@Table(name = "user_roles")
public class UserRoles {

    private int id;
    private String email;
    private Users users;
    private String role;

    public UserRoles() {
    }

    public UserRoles(Users users, String email, String role) {
        this.users = users;
        this.role = role;
        this.email = email;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")

    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "role", nullable = false, length = 45)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

