package MVC.Controller.Company.Goods;

import MVC.Controller.WelcomeC;
import MVC.Model.Entity.Company.Goods.CompanyCatalog;
import MVC.Model.Entity.Report.Mailing;
import MVC.Model.Entity.Report.Orders;
import MVC.Model.Entity.Store.Goods.StoreCatalog;
import MVC.Model.Service.Company.Goods.ICompanyCatalogService;
import MVC.Model.Service.Company.ICompanyService;
import MVC.Model.Service.Report.IOrdersService;
import MVC.Model.Service.Store.Goods.IStoreCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by oleg on 27.05.2015.
 */

@Controller
public class CompanyOrderC {

    @Autowired
    IOrdersService ordersService;

    @Autowired
    ICompanyCatalogService companyCatalogService;

    @Autowired
    IStoreCatalogService storeCatalogService;

    @Autowired
    ICompanyService companyService;

    @Autowired
    JavaMailSender javaMailSender;

    @RequestMapping(value = "/company_orders{id}",  method = RequestMethod.GET)
    public ModelAndView confirmOrder(@PathVariable int id, @ModelAttribute("orders")Orders orders) {
        ModelAndView modelAndView = new ModelAndView("Company/Goods/company_orders");

        try {
            if (!WelcomeC.currentUser.getEmail().equals(companyService.getById(id).getUsers().getEmail()))
                return new ModelAndView("403Page");
        } catch(NullPointerException e){
            return new ModelAndView("403Page");
        }

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("company_id", id);
        modelAndView.addObject("orders", ordersService.getAllByCompany(id, WelcomeC.currentUser.getEmail()));
        return modelAndView;
    }

    @RequestMapping(value = "/confirm_order_success", method = RequestMethod.POST)
    public @ResponseBody
    String confirmOrderSuccessPost(@RequestParam int idOrder)  {

        //confirm order
        Orders orders = ordersService.getById(idOrder);

        //del amount goods from company
        CompanyCatalog companyCatalog = companyCatalogService.getById(orders.getCompanyCatalog().getId());
        String email = orders.getStoreCatalog().getStore().getUsers().getEmail();
        String goods = orders.getStoreCatalog().getName();
        String store = orders.getStoreCatalog().getStore().getName();

        if( (companyCatalog.getAmount() - orders.getAmount()) < 0)
            return "" + companyCatalog.getAmount();
        else {
            orders.setConfirm(1);
            ordersService.update(orders);

            companyCatalog.setAmount(companyCatalog.getAmount() - orders.getAmount());
            companyCatalogService.update(companyCatalog);

            Mailing mailing = new Mailing();
            mailing.setMailSender(javaMailSender);

            mailing.sendMail("robannnnn@gmail.com",
                    email,
                    "StoreBase: Замовлення підтверджено!",
                    "Ваше замовлення (товар - '" + goods + "' з магазина '" + store + "', кількість: '" + orders.getAmount() + "') було розглянуто та підтвердженно, виконується доставка.");

            return "success";
        }
    }

    @RequestMapping(value = "/confirm_order_fail", method = RequestMethod.POST)
    public @ResponseBody
    String confirmOrderFailPost(@RequestParam int idOrder) {

        //unconfirm order
        Orders orders = ordersService.getById(idOrder);
        String email = orders.getStoreCatalog().getStore().getUsers().getEmail();
        String goods = orders.getStoreCatalog().getName();
        String store = orders.getStoreCatalog().getStore().getName();

        orders.setConfirm(-1);
        ordersService.update(orders);

/*
        Mailing mailing = new Mailing();
        mailing.setMailSender(javaMailSender);

        mailing.sendMail("robannnnn@gmail.com",
                email,
                "StoreBase: Замовлення скасовано!",
                "Ваше замовлення (товар - '" + goods + "' з магазина '" + store + "', кількість: '" + orders.getAmount() + "') було розглянуто та скасовано, зверніться, будь ласка, до власника магазина.");
*/

        return "success";
    }

    //confirmed order
    @RequestMapping(value = "/company_confirmed_orders{id}",  method = RequestMethod.GET)
    public ModelAndView confirmedOrder(@PathVariable int id, @ModelAttribute("orders")Orders orders) {
        ModelAndView modelAndView = new ModelAndView("Company/Goods/company_confirmed_orders");

        try {
            if (!WelcomeC.currentUser.getEmail().equals(companyService.getById(id).getUsers().getEmail()))
                return new ModelAndView("403Page");
        } catch(NullPointerException e){
            return new ModelAndView("403Page");
        }

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("orders", ordersService.getAllConfirmedByCompany(id, WelcomeC.currentUser.getEmail()));
        return modelAndView;
    }

    //confirmed order post success
    @RequestMapping(value = "/confirmed_order_success", method = RequestMethod.POST)
    public @ResponseBody String confirmedOrderSuccessPost(@RequestParam int idOrder) {

        //confirm order
        Orders order = ordersService.getById(idOrder);
        String email = order.getStoreCatalog().getStore().getUsers().getEmail();
        String goods = order.getStoreCatalog().getName();
        String store = order.getStoreCatalog().getStore().getName();

        order.setDelivery(true);
        ordersService.update(order);

        //add amount goods in store
        StoreCatalog storeCatalog = storeCatalogService.getById(order.getStoreCatalog().getId());
        storeCatalog.setAmount((storeCatalog.getAmount() + order.getAmount()));
        storeCatalogService.update(storeCatalog);

/*
        Mailing mailing = new Mailing();
        mailing.setMailSender(javaMailSender);

        mailing.sendMail("robannnnn@gmail.com",
                email,
                "StoreBase: Замовлення доставлено!",
                "Ваше замовлення (товар - '" + goods + "' з магазина '" + store + "', кількість: '" + order.getAmount() + "') було доставлено, дякуємо за замовлення!.");
*/

        return "success";
    }

    //confirmed order post fail
    @RequestMapping(value = "/confirmed_order_fail", method = RequestMethod.POST)
    public @ResponseBody String confirmedOrderFailPost(@RequestParam int idOrder) {

        //confirm order
        Orders order = ordersService.getById(idOrder);
        String email = order.getStoreCatalog().getStore().getUsers().getEmail();
        String goods = order.getStoreCatalog().getName();
        String store = order.getStoreCatalog().getStore().getName();

        order.setConfirm(-1);
        ordersService.update(order);

        //add back amount goods to company
        CompanyCatalog companyCatalog = companyCatalogService.getById(order.getCompanyCatalog().getId());
        companyCatalog.setAmount((companyCatalog.getAmount() + order.getAmount()));
        companyCatalogService.update(companyCatalog);

/*
        Mailing mailing = new Mailing();
        mailing.setMailSender(javaMailSender);

        mailing.sendMail("robannnnn@gmail.com",
                email,
                "StoreBase: Замовлення скасовано!",
                "Ваше замовлення (товар - '" + goods + "' з магазина '" + store + "', кількість: '" + order.getAmount() + "') було розглянуто та скасовано, зверніться, будь ласка, до власника магазина.");

*/
        return "success";
    }

    //confirmed order post paid
    @RequestMapping(value = "/confirmed_order_paid", method = RequestMethod.POST)
    public @ResponseBody String confirmedOrderPaidPost(@RequestParam int idOrder) {

        //confirm order
        Orders orders = ordersService.getById(idOrder);
        String email = orders.getStoreCatalog().getStore().getUsers().getEmail();
        String goods = orders.getStoreCatalog().getName();
        String store = orders.getStoreCatalog().getStore().getName();

        if(!orders.isDelivery()) {
            orders.setDelivery(true);
            //add amount goods in store
            StoreCatalog storeCatalog = storeCatalogService.getById(orders.getStoreCatalog().getId());
            storeCatalog.setAmount((storeCatalog.getAmount() + orders.getAmount()));
            storeCatalogService.update(storeCatalog);
        }
        orders.setPaid(true);
        ordersService.update(orders);

/*
        Mailing mailing = new Mailing();
        mailing.setMailSender(javaMailSender);

        mailing.sendMail("robannnnn@gmail.com",
                email,
                "StoreBase: Замовлення оплачено!",
                "Ваше замовлення (товар - '" + goods + "' з магазина '" + store + "', кількість: '" + orders.getAmount() + "') було оплачено, дякуємо!.");
*/

        return "success";
    }

}
