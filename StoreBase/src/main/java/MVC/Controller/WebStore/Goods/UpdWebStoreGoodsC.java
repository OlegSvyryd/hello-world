package MVC.Controller.WebStore.Goods;

import MVC.Controller.WelcomeC;
import MVC.Model.Entity.WebStore.BookGoods;
import MVC.Model.Entity.WebStore.ShoeGoods;
import MVC.Model.Service.WebStore.IBookGoodsService;
import MVC.Model.Service.WebStore.IShoeGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by oleg on 07.06.2015.
 */

@Controller
public class UpdWebStoreGoodsC {

    @Autowired
    IBookGoodsService bookGoodsService;

    @Autowired
    IShoeGoodsService shoeGoodsService;

    private BookGoods bookGoodsClone;
    private ShoeGoods shoeGoodsClone;

    //update book goods
    @RequestMapping(value = "/update_web_store_book_goods{id}", method = RequestMethod.GET)
    public ModelAndView updBookGoods(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("WebStore/Goods/update_web_store_book_goods");

        BookGoods bookGoods = bookGoodsService.getById(id);
        bookGoodsClone = bookGoods;
        try {
            modelAndView.addObject("goods", bookGoods);
            modelAndView.addObject("username", WelcomeC.username);
            return modelAndView;
        }
        catch(Exception e){
            return new ModelAndView("redirect:my_web_store?error");
        }
    }

    //send post update book goods
    @RequestMapping(value = "/update_web_store_book_goods_success", method = RequestMethod.POST)
    public ModelAndView updBookGoodsPost(@Valid @ModelAttribute("goods")BookGoods bookGoods) {
        ModelAndView modelAndView = new ModelAndView("redirect:web_store_goods" + bookGoods.getWebStore().getId());

        bookGoods.setImage(bookGoodsClone.getImage());
        bookGoodsService.update(bookGoods);
        return modelAndView;
    }

    //update shoe goods
    @RequestMapping(value = "/update_web_store_shoe_goods{id}", method = RequestMethod.GET)
    public ModelAndView updShoeGoods(@PathVariable int id, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("WebStore/Goods/update_web_store_shoe_goods");

        int[] arr = new int[31];
        for(int i = 25, j = 0; i <= 55 && j <= 30; i++, j++)
            arr[j]=i;
        model.addAttribute("sizel", arr);

        ShoeGoods shoeGoods = shoeGoodsService.getById(id);
        shoeGoodsClone = shoeGoods;
        try {
            modelAndView.addObject("goods", shoeGoods);
            modelAndView.addObject("username", WelcomeC.username);
            return modelAndView;
        }
        catch(Exception e){
            return new ModelAndView("redirect:my_web_store?error");
        }
    }

    //send post update shoe goods
    @RequestMapping(value = "/update_web_store_shoe_goods_success", method = RequestMethod.POST)
    public ModelAndView updBookGoodsPost(@Valid @ModelAttribute("goods")ShoeGoods shoeGoods) {
        ModelAndView modelAndView = new ModelAndView("redirect:web_store_shoe_goods" + shoeGoods.getWebStore().getId());

        shoeGoods.setImage(shoeGoodsClone.getImage());
        shoeGoodsService.update(shoeGoods);
        return modelAndView;
    }

}
