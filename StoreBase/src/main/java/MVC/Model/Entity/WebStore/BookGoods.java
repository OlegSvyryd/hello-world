package MVC.Model.Entity.WebStore;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.codec.Base64;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by oleg on 05.06.2015.
 */

@Entity
@Table(name = "book_goods")
public class BookGoods {

    private int id;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 30, message = "Помилка, поле занадто довге")
    private String name;

    @NotNull(message = "Помилка, поле не може бути порожнє")
    private BigDecimal price;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 50, message = "Помилка, поле занадто довге")
    private String author;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 25, message = "Помилка, поле занадто довге")
    private String lang;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 100, message = "Помилка, поле занадто довге")
    private String genre;

    private int page;
    private int year;

    @Length(max = 25, message = "Помилка, поле занадто довге")
    private String isbn;

    private boolean inStock;

    @Lob
    private byte[] image;

    private WebStore webStore;

    public BookGoods() {}

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

    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "lang")
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Column(name = "genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Column(name = "page")
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Column(name = "isbn")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

/*
    @Transient
    public String getByteArrayImage() {
        try {
            return new String(new Base64().encode(this.image));
        } catch(NullPointerException e){

        }
        return null;
    }
*/

    @Override
    public String toString() {
        return "BookGoods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", author='" + author + '\'' +
                ", lang='" + lang + '\'' +
                ", genre='" + genre + '\'' +
                ", page=" + page +
                ", year=" + year +
                ", isbn='" + isbn + '\'' +
                ", image='" + image + '\'' +
                ", inStock=" + inStock +
                '}';
    }
}
