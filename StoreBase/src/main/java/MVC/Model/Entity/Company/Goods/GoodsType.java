package MVC.Model.Entity.Company.Goods;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by oleg on 26.05.2015.
 */

@Entity
@Table(name = "goods_type")
public class GoodsType {
    private int id;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 25, message = "Помилка, поле занадто довге")
    private String name;
    private int parentId;

    private Set<CompanyCatalog> companyCatalogs;

    public GoodsType() {
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

    @Column(name = "parent_id")
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "goodsType")
    public Set<CompanyCatalog> getCompanyCatalogs() {
        return companyCatalogs;
    }

    public void setCompanyCatalogs(Set<CompanyCatalog> companyCatalogs) {
        this.companyCatalogs = companyCatalogs;
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
