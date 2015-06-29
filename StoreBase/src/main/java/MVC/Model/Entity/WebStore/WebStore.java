package MVC.Model.Entity.WebStore;

import MVC.Model.Entity.Users.Users;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by oleg on 05.06.2015.
 */

@Entity
@Table(name = "web_store")
public class WebStore {

    private int id;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 30, message = "Помилка, поле занадто довге")
    private String name;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 150, message = "Помилка, поле занадто довге")
    private String address;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Email(message = "Помилка, некоректний емейл")
    @Length(max = 30, message = "Помилка, поле занадто довге")
    private String email;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 17, message = "Помилка, поле занадто довге")
    private String phone;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 50, message = "Помилка, поле занадто довге")
    private String url;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 250, message = "Помилка, поле занадто довге")
    private String description;

    private int type_id;

    private Users users;
    private Set<ShoeGoods> shoeGoods;
    private Set<BookGoods> bookGoods;

    public WebStore() {
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

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "type_id")
    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "webStore")
    public Set<ShoeGoods> getShoeGoods() {
        return shoeGoods;
    }

    public void setShoeGoods(Set<ShoeGoods> shoeGoods) {
        this.shoeGoods = shoeGoods;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "webStore")
    public Set<BookGoods> getBookGoods() {
        return bookGoods;
    }

    public void setBookGoods(Set<BookGoods> bookGoods) {
        this.bookGoods = bookGoods;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "WebStore{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", type_id=" + type_id +
                '}';
    }
}
