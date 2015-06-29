package MVC.Model.DAO.Company.Goods;

import MVC.Model.Entity.Company.Goods.CompanyCatalog;

import java.util.List;

/**
 * Created by oleg on 26.05.2015.
 */
public interface CompanyCatalogDAO {
    public void insert(CompanyCatalog companyCatalog);
    public void update(CompanyCatalog companyCatalog);
    public void delete(CompanyCatalog companyCatalog);
    public CompanyCatalog getById(int id);
    public List<CompanyCatalog> getAll(int company_id);
    public List<CompanyCatalog> getAll(int company_id, String email);
}
