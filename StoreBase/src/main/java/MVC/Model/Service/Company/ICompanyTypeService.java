package MVC.Model.Service.Company;

import MVC.Model.Entity.Company.CompanyType;

import java.util.List;

/**
 * Created by oleg on 23.05.2015.
 */
public interface ICompanyTypeService {
    public void insert(CompanyType companyType);
    public void delete(CompanyType companyType);
    public CompanyType getById(int id);
    public List<CompanyType> getAll();
}
