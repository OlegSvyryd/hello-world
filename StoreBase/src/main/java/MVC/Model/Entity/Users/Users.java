package MVC.Model.Entity.Users;

import MVC.Model.Entity.Company.Company;
import MVC.Model.Entity.Store.Store;
import MVC.Model.Entity.WebStore.WebStore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by oleg on 19.05.2015.
 */

@Entity
@Table(name = "users")
public class Users {

    private int id;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 15, message = "Помилка, поле занадто довге")
    private String name;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 25, message = "Помилка, поле занадто довге")
    private String surname;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 60, message = "Помилка, поле занадто довге")
    private String password;

    @Transient
    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 60, message = "Помилка, поле занадто довге")
    private String confirmPassword;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Email(message = "Помилка, некоректний емейл")
    @Length(max = 30, message = "Помилка, поле занадто довге")
    private String email;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 17, message = "Помилка, поле занадто довге")
    private String phone;

    private boolean enabled;
    private Set<UserRoles> userRoles = new HashSet<UserRoles>(0);
    private Set<Store> stores = new HashSet<>(0);
    private Set<Company> companies = new HashSet<>(0);
    private Set<WebStore> web_stores = new HashSet<>(0);

    public Users() {
    }

    public Users(String password, String email, boolean enabled) {
        this.password = password;
        this.email = email;
        this.enabled = enabled;
    }

    public Users(String password, String email, boolean enabled, Set<UserRoles> userRoles) {
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.userRoles = userRoles;
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

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "password")
    public String getPassword(){ return password; }

    public void setPassword(String password) { this.password = password; }

    @Transient
    public String getConfirmPassword() {
        return confirmPassword;
    }

    @Transient
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "enabled", nullable = false)
    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    public Set<UserRoles> getUserRoles() {
        return this.userRoles;
    }

    public void setUserRoles(Set<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    public Set<WebStore> getWebStores() {
        return web_stores;
    }

    public void setWebStores(Set<WebStore> webStores) {
        this.web_stores = webStores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (enabled != users.enabled) return false;
        if (id != users.id) return false;
        if (confirmPassword != null ? !confirmPassword.equals(users.confirmPassword) : users.confirmPassword != null)
            return false;
        if (!email.equals(users.email)) return false;
        if (!name.equals(users.name)) return false;
        if (!password.equals(users.password)) return false;
        if (!phone.equals(users.phone)) return false;
        if (!surname.equals(users.surname)) return false;
        if (!userRoles.equals(users.userRoles)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (confirmPassword != null ? confirmPassword.hashCode() : 0);
        result = 31 * result + email.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + userRoles.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
