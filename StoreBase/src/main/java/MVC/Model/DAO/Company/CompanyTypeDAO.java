package MVC.Model.DAO.Company;

import MVC.Model.Entity.Company.CompanyType;

import java.util.List;

/**
 * Created by oleg on 22.05.2015.
 */
public interface CompanyTypeDAO {
    public void insert(CompanyType companyType);
    public void delete(CompanyType companyType);
    public CompanyType getById(int id);
    public List<CompanyType> getAll();
}
