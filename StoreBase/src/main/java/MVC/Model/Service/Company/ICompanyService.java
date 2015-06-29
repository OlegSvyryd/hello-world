package MVC.Model.Service.Company;

import MVC.Model.Entity.Company.Company;

import java.util.List;

/**
 * Created by oleg on 23.05.2015.
 */
public interface ICompanyService {
    public void insert(Company company);
    public void update(Company company);
    public void delete(Company company);
    public Company getById(int id);
    public List<Company> getAllByType(int type_id);
    public List<Company> getAll(int directorId);
}
