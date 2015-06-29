package MVC.Controller.Store.Goods;

import MVC.Controller.WelcomeC;
import MVC.Model.Entity.Report.Orders;
import MVC.Model.Service.Report.IOrdersService;
import MVC.Model.Service.Store.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by oleg on 27.05.2015.
 */

@Controller
public class StoreOrderC {

    @Autowired
    IOrdersService ordersService;

    @Autowired
    IStoreService storeService;

    @RequestMapping(value = "/check_result_order{id}", method = RequestMethod.GET)
    public ModelAndView checkResultOrder(@PathVariable int id, @ModelAttribute("orders") Orders orders) {
        ModelAndView modelAndView = new ModelAndView("Store/Goods/check_result_order");

        try {
            if (!WelcomeC.currentUser.getEmail().equals(storeService.getById(id).getUsers().getEmail()))
                return new ModelAndView("403Page");
        } catch(NullPointerException e){
            return new ModelAndView("403Page");
        }

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("orders", ordersService.getAllByStore(id, WelcomeC.currentUser.getEmail()));
        return modelAndView;
    }

    @RequestMapping(value = "/hide_order_checking", method = RequestMethod.POST)
    public @ResponseBody
    void hideOrder(@RequestParam int idOrder) {

        //hide order
        Orders orders = ordersService.getById(idOrder);
        orders.setVisible(false);
        ordersService.update(orders);
    }
}
