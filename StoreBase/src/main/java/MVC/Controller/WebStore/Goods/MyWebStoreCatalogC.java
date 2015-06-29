package MVC.Controller.WebStore.Goods;

import MVC.Controller.WelcomeC;
import MVC.Model.Entity.Company.Goods.CompanyCatalog;
import MVC.Model.Entity.Report.Orders;
import MVC.Model.Entity.Store.Goods.StoreCatalog;
import MVC.Model.Entity.WebStore.BookGoods;
import MVC.Model.Service.Company.Goods.ICompanyCatalogService;
import MVC.Model.Service.Report.IOrdersService;
import MVC.Model.Service.Store.Goods.IStoreCatalogService;
import MVC.Model.Service.WebStore.IBookGoodsService;
import MVC.Model.Service.WebStore.IShoeGoodsService;
import MVC.Model.Service.WebStore.IWebStoreService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by oleg on 26.05.2015.
 */

@Controller
public class MyWebStoreCatalogC {

    @Autowired
    IBookGoodsService bookGoodsService;

    @Autowired
    IShoeGoodsService shoeGoodsService;

    @Autowired
    IWebStoreService webStoreService;

    //catalog goods
    @RequestMapping(value = "/web_store_goods{id}")
    public ModelAndView bookGoods(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("WebStore/Goods/web_store_book_goods");

        try {
            if (!WelcomeC.currentUser.getEmail().equals(webStoreService.getById(id).getUsers().getEmail()) || webStoreService.getById(id).getType_id() == 2) {
                return new ModelAndView("403Page");
            }
        } catch(NullPointerException e){
            return new ModelAndView("403Page");
        }

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("web_store_id", id);
        modelAndView.addObject("web_store_goods", bookGoodsService.getAll(id, WelcomeC.currentUser.getEmail()));
        return modelAndView;
    }

    //catalog goods
    @RequestMapping(value = "/web_store_shoe_goods{id}")
    public ModelAndView shoeGoods(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("WebStore/Goods/web_store_shoe_goods");

        try {
            if (!WelcomeC.currentUser.getEmail().equals(webStoreService.getById(id).getUsers().getEmail()) || webStoreService.getById(id).getType_id() == 1) {
                return new ModelAndView("403Page");
            }
        } catch(NullPointerException e){
            return new ModelAndView("403Page");
        }

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("web_store_id", id);
        modelAndView.addObject("web_store_goods", shoeGoodsService.getAll(id, WelcomeC.currentUser.getEmail()));
        return modelAndView;
    }

    //ajax delete book goods
    @RequestMapping(value = "del_web_store_goods_book_success", method = RequestMethod.POST)
    public @ResponseBody
    String delBookGoodsPost(@RequestParam int idGoods) {
        bookGoodsService.delete(bookGoodsService.getById(idGoods));
        return "success";
    }

    //ajax delete shoe goods
    @RequestMapping(value = "del_web_store_goods_shoe_success", method = RequestMethod.POST)
    public @ResponseBody
    String delShoeGoodsPost(@RequestParam int idGoods) {
        shoeGoodsService.delete(shoeGoodsService.getById(idGoods));
        return "success";
    }

}
