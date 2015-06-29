package MVC.Model.DAO.WebStore;

import MVC.Model.Entity.WebStore.BookGoods;

import java.util.List;

/**
 * Created by oleg on 05.06.2015.
 */
public interface BookGoodsDAO {
    public void insert(BookGoods bookGoods);
    public void update(BookGoods bookGoods);
    public void delete(BookGoods bookGoods);
    public BookGoods getById(int id);
    public List<BookGoods> getAll(int webStoreId);
    public List<BookGoods> getAll(int webStoreId, String email);
}
