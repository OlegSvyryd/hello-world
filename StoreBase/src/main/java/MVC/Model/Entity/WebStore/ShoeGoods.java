package MVC.Model.Entity.WebStore;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.codec.Base64;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by oleg on 05.06.2015.
 */

@Entity
@Table(name = "shoe_goods")
public class ShoeGoods {

    private int id;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 30, message = "Помилка, поле занадто довге")
    private String name;

    @NotNull(message = "Помилка, поле не може бути порожнє")
    private BigDecimal price;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 200, message = "Помилка, поле занадто довге")
    private String description;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 100, message = "Помилка, поле занадто довге")
    private String size;

    private boolean inStock;

    private WebStore webStore;

    @Lob
    private byte[] image;

    public ShoeGoods() {
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

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "size")
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Column(name = "in_stock")
    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    @Column(name = "image")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "web_store_id", nullable = false)
    public WebStore getWebStore() {
        return webStore;
    }

    public void setWebStore(WebStore webStore) {
        this.webStore = webStore;
    }

    @Transient
    public String getByteArrayImage() {
        try {
            return new String(new Base64().encode(this.image));
        } catch(NullPointerException e){

        }
        return null;
    }

    @Override
    public String toString() {
        return "ShoeGoods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", size=" + size +
                ", image=" + image +
                ", inStock=" + inStock +
                '}';
    }
}
