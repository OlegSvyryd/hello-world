package MVC.Controller.WebStore.ClientSide;

import MVC.Model.Entity.WebStore.BookGoods;
import MVC.Model.Entity.WebStore.ShoeGoods;
import MVC.Model.Entity.WebStore.WebOrders;
import MVC.Model.Entity.WebStore.WebStore;
import MVC.Model.Service.WebStore.IBookGoodsService;
import MVC.Model.Service.WebStore.IShoeGoodsService;
import MVC.Model.Service.WebStore.IWebOrdersService;
import MVC.Model.Service.WebStore.IWebStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * Created by oleg on 08.06.2015.
 */

@Controller
public class WebStoreClient {

    @Autowired
    IWebStoreService webStoreService;

    @Autowired
    IShoeGoodsService shoeGoodsService;

    @Autowired
    IBookGoodsService bookGoodsService;

    @Autowired
    IWebOrdersService webOrdersService;

    private String url;
    private int idGoods;
    private boolean isBookGoods = false;

    @RequestMapping(value = "/web-store/{url}", method = RequestMethod.GET)
    public ModelAndView addWebOrder(@PathVariable String url, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView("WebStore/Online/web-store");

        this.url = url;
        String urlClone = new String(url);
        List<ShoeGoods> shoeGoodsList = null;
        List<BookGoods> bookGoodsList = null;

        int[] arr = new int[26];
        for(int i = 1; i <= 25; i++)
            arr[i]=i;

        WebStore webStore = webStoreService.getByURL(urlClone);

        if(webStore == null){
            return new ModelAndView("403Page");
        }
        if(webStore.getType_id() == 1){
            isBookGoods = true;
            bookGoodsList = bookGoodsService.getAll(webStore.getId());

            modelMap.addAttribute("amount_order", arr);
            modelAndView.addObject("order", new WebOrders());
            modelAndView.addObject("title", webStore.getName());
            modelAndView.addObject("book_goods", bookGoodsList);
            modelAndView.addObject("web_store", webStore);

            return modelAndView;
        }
        else if(webStore.getType_id() == 2) {
            isBookGoods = false;
            try {
                shoeGoodsList = shoeGoodsService.getAll(webStore.getId());
            } catch (NullPointerException e) {
            }

            String[] sizes = null;
            try {
                for (int i = 0; i < shoeGoodsList.size(); i++) {
                    sizes = (shoeGoodsList.get(i).getSize().split(","));
                }
            } catch (NullPointerException e) {
            }
                modelMap.addAttribute("sizes", sizes);
                modelMap.addAttribute("amount_order", arr);
                modelAndView.addObject("order", new WebOrders());
                modelAndView.addObject("title", webStore.getName());
                modelAndView.addObject("shoe_goods", shoeGoodsList);
                modelAndView.addObject("web_store", webStore);
        }
        else
            return new ModelAndView("403Page");
        return modelAndView;
    }

    //Post method
    @RequestMapping(value = "/web-order-during", method = RequestMethod.POST)
    public ModelAndView addWebOrderPost(@Valid @ModelAttribute("order") WebOrders webOrders, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("redirect:web-store/" + url);

        if(bindingResult.hasErrors()){
            ModelAndView modelAndView1 = new ModelAndView("WebStore/Online/web-store");
            modelAndView.addObject("goods", webOrders);
            modelAndView.addObject("error_list", "Помилка, будь-ласка введіть коректні дані");
            return modelAndView1;
        }
        else {
            webOrders.setDate(new Timestamp(Calendar.getInstance().getTime().getTime()));

            if(isBookGoods)
                webOrders.setPrice(bookGoodsService.getById(idGoods).getPrice());
            else
                webOrders.setPrice(shoeGoodsService.getById(idGoods).getPrice());

            if(webOrders.getSize() == 0)
                webOrders.setBook_goods_id(idGoods);
            else
                webOrders.setShoe_goods_id(idGoods);

                webOrdersService.insert(webOrders);
                return modelAndView;
        }
    }

    @RequestMapping(value = "/web-order-during-id", method = RequestMethod.POST)
    public @ResponseBody
    String getGoodsId(@RequestParam int idGoods){
        this.idGoods = idGoods;
        return "success";
    }

    @RequestMapping(value = "/imageS/{imageId}")
    @ResponseBody
    public byte[] shoeImage(@PathVariable int imageId) {
        return shoeGoodsService.getById(imageId).getImage();
    }

    @RequestMapping(value = "/imageB/{imageId}")
    @ResponseBody
    public byte[] bookImage(@PathVariable int imageId) {
        return bookGoodsService.getById(imageId).getImage();
    }
}
