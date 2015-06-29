package MVC.Controller.WebStore.Goods;

import MVC.Controller.WelcomeC;
import MVC.Model.Entity.Report.Mailing;
import MVC.Model.Entity.WebStore.WebOrders;
import MVC.Model.Service.WebStore.IWebOrdersService;
import MVC.Model.Service.WebStore.IWebStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by oleg on 15.06.2015.
 */

@Controller
public class WebStoreOrdersC {

    @Autowired
    IWebOrdersService webOrdersService;

    @Autowired
    IWebStoreService webStoreService;

    @Autowired
    JavaMailSender javaMailSender;

    @RequestMapping(value = "/web_store_book_orders{id}",  method = RequestMethod.GET)
    public ModelAndView confirmBookOrder(@PathVariable int id, @ModelAttribute("orders")WebOrders webOrders) {
        ModelAndView modelAndView = new ModelAndView("WebStore/Goods/web_store_orders");

        try {
            if (!WelcomeC.currentUser.getEmail().equals(webStoreService.getById(id).getUsers().getEmail()) || webStoreService.getById(id).getType_id() == 2)
                return new ModelAndView("403Page");
        } catch(NullPointerException e){
            return new ModelAndView("403Page");
        }

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("web_store_id", id);
        modelAndView.addObject("orders", webOrdersService.queueOrdersBook(id, WelcomeC.currentUser.getEmail()));
        return modelAndView;
    }

    @RequestMapping(value = "/web_store_shoe_orders{id}",  method = RequestMethod.GET)
    public ModelAndView confirmShoeOrder(@PathVariable int id, @ModelAttribute("orders")WebOrders webOrders) {
        ModelAndView modelAndView = new ModelAndView("WebStore/Goods/web_store_orders");

        try {
            if (!WelcomeC.currentUser.getEmail().equals(webStoreService.getById(id).getUsers().getEmail()) || webStoreService.getById(id).getType_id() == 1)
                return new ModelAndView("403Page");
        } catch(NullPointerException e){
            return new ModelAndView("403Page");
        }

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("web_store_id", id);
        modelAndView.addObject("orders", webOrdersService.queueOrdersShoe(id, WelcomeC.currentUser.getEmail()));
        return modelAndView;
    }

    @RequestMapping(value = "/confirm_web_order_success", method = RequestMethod.POST)
    public @ResponseBody
    String confirmOrderSuccessPost(@RequestParam int idOrder)  {

        //confirm order
        WebOrders webOrders = webOrdersService.getById(idOrder);
        String name = webOrders.getName();
        String email = webOrders.getEmail();

            webOrders.setConfirm(1);
            webOrdersService.update(webOrders);

/*
        Mailing mailing = new Mailing();
        mailing.setMailSender(javaMailSender);

        mailing.sendMail("robannnnn@gmail.com",
                email,
                "StoreBase: Замовлення прийнято!",
                name + ", Ваше замовлення прийнято та наразі розглядається, очікуйте дзвінка!.");
*/

        return "success";
    }

    @RequestMapping(value = "/confirm_web_order_fail", method = RequestMethod.POST)
    public @ResponseBody
    String confirmOrderFailPost(@RequestParam int idOrder) {

        //unconfirm order
        WebOrders webOrders = webOrdersService.getById(idOrder);
        String name = webOrders.getName();
        String email = webOrders.getEmail();

        webOrders.setConfirm(-1);
        webOrdersService.update(webOrders);

        Mailing mailing = new Mailing();
        mailing.setMailSender(javaMailSender);

        mailing.sendMail("robannnnn@gmail.com",
                email,
                "StoreBase: Замовлення скасовано!",
                name + ", Ваше замовлення скасовано, вибачте за незручності!.");

        return "success";
    }

    //confirmed order post paid
    @RequestMapping(value = "/confirmed_web_order_paid", method = RequestMethod.POST)
    public @ResponseBody
    String confirmedOrderPaidPost(@RequestParam int idOrder) {

        //confirm order
        WebOrders webOrders = webOrdersService.getById(idOrder);
        String name = webOrders.getName();
        String email = webOrders.getEmail();

        if(webOrders.getConfirm() == 0)
            webOrders.setConfirm(1);

        webOrders.setPaid(true);
        webOrdersService.update(webOrders);

        Mailing mailing = new Mailing();
        mailing.setMailSender(javaMailSender);

        mailing.sendMail("robannnnn@gmail.com",
                email,
                "StoreBase: Замовлення оплачено!",
                name + ", дякуємо за замовлення!.");

        return "success";
    }
}
