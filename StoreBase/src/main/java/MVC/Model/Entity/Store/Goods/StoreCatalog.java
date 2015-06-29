package MVC.Model.Entity.Store.Goods;

import MVC.Model.Entity.Company.Goods.CompanyCatalog;
import MVC.Model.Entity.Report.Orders;
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
@Table(name = "store_catalog")
public class StoreCatalog {
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

    @Length(max = 500, message = "Помилка, поле занадто довге")
    private String description;

    private Integer companyGoodsId;

    @Length(max = 50, message = "Помилка, поле занадто довге")
    private String companyName;

    @Length(max = 25, message = "Помилка, поле занадто довге")
    private String goodsType;

    private Store store;
    private Set<Orders> ordersSet;

    public StoreCatalog(){}

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

    @Column(name = "company_goods_id")
    public Integer getCompanyGoodsId() {
        return companyGoodsId;
    }

    public void setCompanyGoodsId(Integer companyGoodsId) {
        this.companyGoodsId = companyGoodsId;
    }

    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column(name = "goods_type")
    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "storeCatalog")
    public Set<Orders> getOrdersSet() {
        return ordersSet;
    }

    public void setOrdersSet(Set<Orders> ordersSet) {
        this.ordersSet = ordersSet;
    }

    @Override
    public String toString() {
        return "StoreCatalog{" +
                "goodsType='" + goodsType + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyGoodsId=" + companyGoodsId +
                ", description='" + description + '\'' +
                ", dimension='" + dimension + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
