package MVC.Model.DAO.Report;


import MVC.Model.Entity.Report.Orders;

import java.util.List;

/**
 * Created by oleg on 26.05.2015.
 */
public interface OrdersDAO {
    public void insert(Orders orders);
    public void update(Orders orders);
    public void delete(Orders orders);
    public Orders getById(int id);
    public List<Orders> getAllByStore(int store_id, String email);
    public List<Orders> getAllByCompany(int company_id, String email);
    public List<Orders> getAllConfirmedByCompany(int company_id, String email);

    //store reports
    public List oborot(int store_id, String beginDate, String endDate);
    public List pruhidTovary(int store_id, String beginDate, String endDate);
    public List prubytok(int store_id, String beginDate, String endDate);
    public List rentabelnistj(int store_id, String beginDate, String endDate);

    //company reports
    public List cOborot(int company_id, String beginDate, String endDate);
    public List cMinStatistic(int company_id, String beginDate, String endDate);
}
