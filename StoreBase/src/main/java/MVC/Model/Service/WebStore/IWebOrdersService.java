package MVC.Model.Service.WebStore;

import MVC.Model.Entity.WebStore.WebOrders;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by oleg on 10.06.2015.
 */

public interface IWebOrdersService {
    public void insert(WebOrders webOrders);
    public void update(WebOrders webOrders);
    public void delete(WebOrders webOrders);
    public WebOrders getById(int id);
    public List<WebOrders> getAll(int web_store_id, String email);
    public List shoeOborot(int web_store_id, String beginDate, String endDate);
    public List bookOborot(int web_store_id, String beginDate, String endDate);
    public List queueOrdersBook(int web_store_id, String email);
    public List queueOrdersShoe(int web_store_id, String email);
}
