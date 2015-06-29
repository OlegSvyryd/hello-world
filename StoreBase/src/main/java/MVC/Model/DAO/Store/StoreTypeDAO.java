package MVC.Model.DAO.Store;

import MVC.Model.Entity.Store.StoreType;

import java.util.List;

/**
 * Created by oleg on 22.05.2015.
 */
public interface StoreTypeDAO {
    public void insert(StoreType storeType);
    public void delete(StoreType storeType);
    public StoreType getById(int id);
    public List<StoreType> getAll();
}
