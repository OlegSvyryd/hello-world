package MVC.Model.DAO.WebStore;

import MVC.Model.Entity.WebStore.WebStore;

import java.util.List;

/**
 * Created by oleg on 05.06.2015.
 */
public interface WebStoreDAO {

    public void insert(WebStore webStore);
    public void update(WebStore webStore);
    public void delete(WebStore webStore);
    public WebStore getById(int id);
    public WebStore getByURL(String url);
    public List getAll();
    public List<WebStore> getAll(int user_id);
    public List<WebStore> getAllByType(int typeId);
}
