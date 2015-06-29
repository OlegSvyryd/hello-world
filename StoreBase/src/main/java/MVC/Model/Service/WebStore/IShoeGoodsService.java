package MVC.Model.Service.WebStore;

import MVC.Model.Entity.WebStore.ShoeGoods;

import java.util.List;

/**
 * Created by oleg on 05.06.2015.
 */
public interface IShoeGoodsService {
    public void insert(ShoeGoods shoeGoods);
    public void update(ShoeGoods shoeGoods);
    public void delete(ShoeGoods shoeGoods);
    public ShoeGoods getById(int id);
    public List<ShoeGoods> getAll(int webStoreId);
    public List<ShoeGoods> getAll(int webStoreId, String email);
}
