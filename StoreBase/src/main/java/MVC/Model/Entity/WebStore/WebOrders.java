package MVC.Model.Entity.WebStore;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by oleg on 10.06.2015.
 */

@Entity
@Table(name = "web_orders")
public class WebOrders {

    private int id;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 25, message = "Помилка, поле занадто довге")
    private String name;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Email(message = "Помилка, некоректний емейл")
    @Length(max = 30, message = "Помилка, поле занадто довге")
    private String email;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 17, message = "Помилка, поле занадто довге")
    private String phone;

    @NotNull
    private int amount;

    private int confirm;
    private boolean paid;

    private Timestamp date;
    private BigDecimal price;

    private int size;
    private int book_goods_id;
    private int shoe_goods_id;

    public WebOrders() {}

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

    @Column(name = "amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Column(name = "size")
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Column(name = "book_goods_id")
    public int getBook_goods_id() {
        return book_goods_id;
    }

    public void setBook_goods_id(int book_goods_id) {
        this.book_goods_id = book_goods_id;
    }

    @Column(name = "shoe_goods_id")
    public int getShoe_goods_id() {
        return shoe_goods_id;
    }

    public void setShoe_goods_id(int shoe_goods_id) {
        this.shoe_goods_id = shoe_goods_id;
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

    @Override
    public String toString() {
        return "WebOrders{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", amount=" + amount +
                ", confirm=" + confirm +
                ", paid=" + paid +
                ", date=" + date +
                ", price=" + price +
                ", size=" + size +
                ", shoe_goods_id=" + shoe_goods_id +
                ", book_goods_id=" + book_goods_id +
                '}';
    }
}
