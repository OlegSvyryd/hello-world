package MVC.Model.Entity.Store;

import MVC.Model.Entity.Store.Goods.StoreCatalog;
import MVC.Model.Entity.Users.Users;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by oleg on 22.05.2015.
 */

@Entity
@Table(name = "store")
public class Store{
    private int id;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 50, message = "Помилка, поле занадто довге")
    private String name;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 100, message = "Помилка, поле занадто довге")
    private String address;

    private StoreType storeType;
    private Users users;
    private Set<StoreCatalog> storeCatalogs;

    public Store() {
    }

    public Store(String name, StoreType storeType, String address) {
        this.name = name;
        this.storeType = storeType;
        this.address = address;
    }

    public Store(Store store, Users users){
        this.name = store.getName();
        this.storeType = store.getStoreType();
        this.address = store.getAddress();
        this.users = users;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    public StoreType getStoreType() {
        return storeType;
    }

    public void setStoreType(StoreType storeType) {
        this.storeType = storeType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id", nullable = false)
    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
    public Set<StoreCatalog> getStoreCatalogs() {
        return storeCatalogs;
    }

    public void setStoreCatalogs(Set<StoreCatalog> storeCatalogs) {
        this.storeCatalogs = storeCatalogs;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
