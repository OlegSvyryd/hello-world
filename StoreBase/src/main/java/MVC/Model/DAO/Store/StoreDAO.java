package MVC.Model.DAO.Store;

import MVC.Model.Entity.Store.Store;

import java.util.List;

/**
 * Created by oleg on 22.05.2015.
 */
public interface StoreDAO {
    public void insert(Store store);
    public void update(Store store);
    public void delete(Store store);
    public Store getById(int id);
    public List<Store> getAllByType(int type_id);
    public List<Store> getAll(int directorId);
    public List<Store> getAllWithoutThisGoods(int user_id, Integer goods_id);
}
