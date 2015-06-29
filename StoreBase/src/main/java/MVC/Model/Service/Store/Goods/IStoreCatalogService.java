package MVC.Model.Service.Store.Goods;

import MVC.Model.Entity.Store.Goods.StoreCatalog;

import java.util.List;

/**
 * Created by oleg on 26.05.2015.
 */
public interface IStoreCatalogService {
    public void insert(StoreCatalog storeCatalog);
    public void update(StoreCatalog storeCatalog);
    public void delete(StoreCatalog storeCatalog);
    public StoreCatalog getById(int id);
    public List<StoreCatalog> getAll(int store_id, String email);
}
