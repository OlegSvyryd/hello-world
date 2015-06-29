package MVC.Model.Entity.Report;

import MVC.Model.Entity.Company.Goods.CompanyCatalog;
import MVC.Model.Entity.Store.Goods.StoreCatalog;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by oleg on 26.05.2015.
 */

@Entity
@Table(name = "orders")
public class Orders {
    private int id;
    private Timestamp date;
    private int amount;
    private int confirm;
    private boolean paid;
    private boolean delivery;
    private boolean visible;
    private int step;
    private BigDecimal storePrice;
    private BigDecimal orderPrice;

    private StoreCatalog storeCatalog;
    private CompanyCatalog companyCatalog;

    public Orders() {
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

    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Column(name = "amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Column(name = "confirm")
    public int getConfirm() {
        return confirm;
    }

    public void setConfirm(int confirm) {
        this.confirm = confirm;
    }

    @Column(name = "paid")
    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Column(name = "delivery")
    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    @Column(name = "visible")
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Column(name = "step")
    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    @Column(name = "store_price")
    public BigDecimal getStorePrice() {
        return storePrice;
    }

    public void setStorePrice(BigDecimal storePrice) {
        this.storePrice = storePrice;
    }

    @Column(name = "order_price")
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_store_id", nullable = false)
    public StoreCatalog getStoreCatalog() {
        return storeCatalog;
    }

    public void setStoreCatalog(StoreCatalog storeCatalog) {
        this.storeCatalog = storeCatalog;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_company_id", nullable = false)
    public CompanyCatalog getCompanyCatalog() {
        return companyCatalog;
    }

    public void setCompanyCatalog(CompanyCatalog companyCatalog) {
        this.companyCatalog = companyCatalog;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", confirm=" + confirm +
                ", paid=" + paid +
                ", delivery=" + delivery +
                ", visible=" + visible +
                ", step=" + step +
                '}';
    }
}
