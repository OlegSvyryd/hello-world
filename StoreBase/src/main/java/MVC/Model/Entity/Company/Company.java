package MVC.Model.Entity.Company;

import MVC.Model.Entity.Company.Goods.CompanyCatalog;
import MVC.Model.Entity.Users.Users;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by oleg on 22.05.2015.
 */

@Entity
@Table(name = "company")
public class Company {
    private int id;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 50, message = "Помилка, поле занадто довге")
    private String name;

    @NotEmpty(message = "Помилка, поле не може бути порожнє")
    @Length(max = 100, message = "Помилка, поле занадто довге")
    private String address;

    private CompanyType companyType;
    private Users users;
    private Set<CompanyCatalog> companyCatalogs;

    public Company() {
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
    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id", nullable = false)
    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    public Set<CompanyCatalog> getCompanyCatalogs() {
        return companyCatalogs;
    }

    public void setCompanyCatalogs(Set<CompanyCatalog> companyCatalogs) {
        this.companyCatalogs = companyCatalogs;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
