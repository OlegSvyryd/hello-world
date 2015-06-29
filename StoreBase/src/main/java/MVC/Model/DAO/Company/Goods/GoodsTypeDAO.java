package MVC.Model.DAO.Company.Goods;

import MVC.Model.Entity.Company.Goods.GoodsType;

import java.util.List;

/**
 * Created by oleg on 26.05.2015.
 */
public interface GoodsTypeDAO {
    public void insert(GoodsType goodsType);
    public void update(GoodsType goodsType);
    public void delete(GoodsType goodsType);
    public GoodsType getById(int id);
    public List<GoodsType> getAll();
}
