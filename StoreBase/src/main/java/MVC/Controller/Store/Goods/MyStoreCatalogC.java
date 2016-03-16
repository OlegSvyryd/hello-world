package MVC.Controller.Store.Goods;

import MVC.Controller.WelcomeC;
import MVC.Model.Entity.Company.Goods.CompanyCatalog;
import MVC.Model.Entity.Report.Orders;
import MVC.Model.Entity.Store.Goods.StoreCatalog;
import MVC.Model.Service.Company.Goods.ICompanyCatalogService;
import MVC.Model.Service.Report.IOrdersService;
import MVC.Model.Service.Store.Goods.IStoreCatalogService;
import MVC.Model.Service.Store.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by oleg on 26.05.2015.
 */

@Controller
public class MyStoreCatalogC {

    @Autowired
    IStoreCatalogService storeCatalogService;

    @Autowired
    ICompanyCatalogService companyCatalogService;

    @Autowired
    IOrdersService ordersService;

    @Autowired
    IStoreService storeService;

    @Autowired
    JavaMailSender javaMailSender;

    //catalog goods
    @RequestMapping(value = "/store_goods{id}")
    public ModelAndView storeGoods(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("Store/Goods/store_goods");

        try {
            if (!WelcomeC.currentUser.getEmail().equals(storeService.getById(id).getUsers().getEmail()))
                return new ModelAndView("403Page");
        } catch(NullPointerException e){
            return new ModelAndView("403Page");
        }

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("store_id", id);
        modelAndView.addObject("store_goods", storeCatalogService.getAll(id, WelcomeC.currentUser.getEmail()));
        return modelAndView;
    }

    //ajax delete goods
    @RequestMapping(value = "del_store_goods_success", method = RequestMethod.POST)
    public @ResponseBody
    String delStoreGoodsPost(@RequestParam int idGoods) {
        storeCatalogService.delete(storeCatalogService.getById(idGoods));
        return "success";
    }

    //ajax add amount
    @RequestMapping(value = "add_amount_goods", method = RequestMethod.POST)
    public @ResponseBody String addAmountGoods(@RequestParam int idGoods, @RequestParam int amount) {
        StoreCatalog storeCatalog = storeCatalogService.getById(idGoods);

        try {
            CompanyCatalog companyCatalog = companyCatalogService.getById(storeCatalog.getCompanyGoodsId());
            String email = companyCatalog.getCompany().getUsers().getEmail();
            String companyName = companyCatalog.getCompany().getName();
            String storeName = storeCatalog.getStore().getName();

        if(companyCatalog.getAmount() < amount) {
            return ""+companyCatalog.getAmount();
        }
        else if(amount < 0){
            return "out";
        }
        else {
            Orders orders = new Orders();
            orders.setStoreCatalog(storeCatalog);
            orders.setCompanyCatalog(companyCatalog);
            orders.setDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
            orders.setAmount(amount);
            orders.setPaid(false);
            orders.setDelivery(false);
            orders.setVisible(true);
            orders.setOrderPrice(companyCatalog.getPrice());
            ordersService.insert(orders);

/*
            Mailing mailing = new Mailing();
            mailing.setMailSender(javaMailSender);

            mailing.sendMail("robannnnn@gmail.com",
                    email,
                    "StoreBase: Нове замовлення!",
                    "Ваш товар '" + companyCatalog.getName() + "' з компанії '" + companyName + "' було замовлено в кількості '" + amount + "' одиниць магазином '" + storeName + "', будь ласка, перегляньте його.");
*/

            return "success";
            }
        } catch(NullPointerException e){
            e.printStackTrace();
            return "stop";
        } catch(Exception e){
            e.printStackTrace();
            return "undef";
        }
    }

    //ajax del amount
    @RequestMapping(value = "del_amount_goods", method = RequestMethod.POST)
    public @ResponseBody String delAmountGoods(@RequestParam int idGoods, @RequestParam int amount) {
        StoreCatalog storeCatalog = storeCatalogService.getById(idGoods);

        CompanyCatalog companyCatalog = null;
        try {
            companyCatalog = companyCatalogService.getById(storeCatalog.getCompanyGoodsId());
        }catch(Exception e){
            companyCatalog = null;
        }

            int oldAmountInStore = storeCatalog.getAmount();

            if (storeCatalog.getAmount() < amount) {
                return "" + storeCatalog.getAmount();
            } else if (amount <= 0) {
                return "out";
            } else {
                Orders orders = new Orders();
                orders.setStoreCatalog(storeCatalog);
                orders.setCompanyCatalog(companyCatalog);
                orders.setDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
                orders.setAmount(amount);
                orders.setPaid(false);
                orders.setDelivery(false);
                orders.setVisible(true);
                orders.setStorePrice(storeCatalog.getPrice());
                orders.setOrderPrice(companyCatalog.getPrice());
                orders.setStep(1);
                ordersService.insert(orders);

                storeCatalog.setAmount(oldAmountInStore - amount);
                storeCatalogService.update(storeCatalog);
                return "success";
            }
    }

    //update goods
    @RequestMapping(value = "/upd_store_goods{id}", method = RequestMethod.GET)
    public ModelAndView updGoods(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("Store/Goods/update_store_goods");

        try {
            modelAndView.addObject("store_goods", storeCatalogService.getById(id));
            modelAndView.addObject("username", WelcomeC.username);
            return modelAndView;
        }
        catch(Exception e){
            return new ModelAndView("redirect:my_store?error");
        }
    }

    //send post update goods
    @RequestMapping(value = "/upd_store_goods_success", method = RequestMethod.POST)
    public ModelAndView updGoodsPost(@Valid @ModelAttribute("store_goods") StoreCatalog storeCatalog) {
        ModelAndView modelAndView = new ModelAndView("redirect:store_goods" + storeCatalog.getStore().getId());

        storeCatalogService.update(storeCatalog);
        return modelAndView;
    }

}
