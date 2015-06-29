package MVC.Model.Entity.Store;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by oleg on 22.05.2015.
 */

@Entity
@Table(name = "store_type")
public class StoreType {
    private int id;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 50, message = "Помилка, поле занадто довге")
    private String name;
    private Set<Store> stores;

    public StoreType() {
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "storeType")
    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }

    @Override
    public String toString() {
        return "StoreType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
