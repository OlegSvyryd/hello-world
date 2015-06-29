package MVC.Model.Service.Store;

import MVC.Model.Entity.Store.StoreType;

import java.util.List;

/**
 * Created by oleg on 23.05.2015.
 */
public interface IStoreTypeService {
    public void insert(StoreType storeType);
    public void delete(StoreType storeType);
    public StoreType getById(int id);
    public List<StoreType> getAll();
}
