package MVC.Model.Entity.Company.Goods;

import MVC.Model.Entity.Company.Company;
import MVC.Model.Entity.Report.Orders;
import MVC.Model.Entity.Store.Goods.StoreCatalog;
import MVC.Model.Entity.Store.Store;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by oleg on 26.05.2015.
 */

@Entity
@Table(name = "company_catalog")
public class CompanyCatalog {
    private int id;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 100, message = "Помилка, поле занадто довге")
    private String name;

    @NotNull(message = "Помилка, поле не може бути порожнє")
    private BigDecimal price;

    @NotNull(message = "Помилка, поле не може бути порожнє")
    private int amount;

    @Length(max = 25, message = "Помилка, поле занадто довге")
    private String dimension;

    @Length(max = 25, message = "Помилка, поле занадто довге")
    private String description;

    private Company company;
    private GoodsType goodsType;
    private Set<Orders> ordersSet;

    public CompanyCatalog(){}

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

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Column(name = "dimension")
    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "companyCatalog")
    public Set<Orders> getOrdersSet() {
        return ordersSet;
    }

    public void setOrdersSet(Set<Orders> ordersSet) {
        this.ordersSet = ordersSet;
    }

    @Override
    public String toString() {
        return "CompanyCatalog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", dimension='" + dimension + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
