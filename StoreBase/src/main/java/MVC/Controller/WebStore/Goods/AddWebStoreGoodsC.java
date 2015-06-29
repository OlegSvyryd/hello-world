package MVC.Controller.WebStore.Goods;

import MVC.Controller.WelcomeC;
import MVC.Model.Entity.WebStore.BookGoods;
import MVC.Model.Entity.WebStore.ShoeGoods;
import MVC.Model.Entity.WebStore.WebStore;
import MVC.Model.Service.WebStore.IBookGoodsService;
import MVC.Model.Service.WebStore.IShoeGoodsService;
import MVC.Model.Service.WebStore.IWebStoreService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

/**
 * Created by oleg on 27.05.2015.
 */

@Controller
public class AddWebStoreGoodsC {

    @Autowired
    IWebStoreService webStoreService;

    @Autowired
    IShoeGoodsService shoeGoodsService;

    @Autowired
    IBookGoodsService bookGoodsService;

    private int webStoreId;
    private int goodsId;

    //add book goods
    @RequestMapping(value = "/add_web_store_book_goods{id}", method = RequestMethod.GET)
    public ModelAndView addBookGoodsInWebStore(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("WebStore/Goods/add_web_store_book_goods", "web_store_goods", new BookGoods());

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("web_store_id", id);
        webStoreId = id;
        return modelAndView;
    }

    //post method add book goods
    @RequestMapping(value = "/add_web_store_book_goods_success", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView addBookGoodsInWebStorePost(@Valid @ModelAttribute("web_store_goods")BookGoods bookGoods) {
        ModelAndView modelAndView = new ModelAndView("redirect:web_store_goods" + webStoreId);

        WebStore webStore = webStoreService.getById(webStoreId);
        bookGoods.setWebStore(webStore);
        bookGoods.setInStock(true);

        bookGoodsService.insert(bookGoods);
        return modelAndView;
    }

    //add shoe goods
    @RequestMapping(value = "/add_web_store_shoe_goods{id}", method = RequestMethod.GET)
    public ModelAndView addShoeGoodsInWebStore(@PathVariable int id, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("WebStore/Goods/add_web_store_shoe_goods", "web_store_goods", new ShoeGoods());

        int[] arr = new int[31];
        for(int i = 25, j = 0; i <= 55 && j <= 30; i++, j++)
            arr[j]=i;
        model.addAttribute("sizel", arr);
        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("web_store_id", id);
        webStoreId = id;
        return modelAndView;
    }

    //post method add shoe goods
    @RequestMapping(value = "/add_web_store_shoe_goods_success", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView addShoeGoodsInWebStorePost(@Valid @ModelAttribute("web_store_goods")ShoeGoods shoeGoods, BindingResult bindingResult, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("redirect:web_store_shoe_goods" + webStoreId);

        if(bindingResult.hasErrors()){
            ModelAndView modelAndView1 = new ModelAndView("WebStore/Goods/add_web_store_shoe_goods");

            int[] arr = new int[31];
            for(int i = 25, j = 0; i <= 55 && j <= 30; i++, j++)
                arr[j]=i;
            model.addAttribute("sizel", arr);

            modelAndView.addObject("error_list", "Помилка, будь-ласка введіть коректні дані");
            return modelAndView1;
        }

        WebStore webStore = webStoreService.getById(webStoreId);
        shoeGoods.setWebStore(webStore);
        shoeGoods.setInStock(true);

        shoeGoodsService.insert(shoeGoods);
        return modelAndView;
    }

    @RequestMapping(value="/upload_book_image", method = RequestMethod.POST)
    public @ResponseBody ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file){
        ModelAndView modelAndView = new ModelAndView("redirect:web_store_goods" +webStoreId);
        try {
            BookGoods bookGoods = bookGoodsService.getById(goodsId);

            bookGoods.setImage(file.getBytes());

            bookGoodsService.update(bookGoods);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value="/upload_shoe_image", method = RequestMethod.POST)
    public @ResponseBody ModelAndView handleFileUpload1(@RequestParam("file") MultipartFile file){
        ModelAndView modelAndView = new ModelAndView("redirect:web_store_shoe_goods" +webStoreId);
        try {
            ShoeGoods shoeGoods =  shoeGoodsService.getById(goodsId);

            shoeGoods.setImage(file.getBytes());

            shoeGoodsService.update(shoeGoods);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value="/get_goods_id_for_image", method = RequestMethod.POST)
    public @ResponseBody void handleFileUpload1(@RequestParam("idGoods") int idGoods, @RequestParam("webStoreId") int webStoreId) {
        this.goodsId=idGoods;
        this.webStoreId=webStoreId;
    }

    @RequestMapping(value = "/imageControllerS/{imageId}")
    @ResponseBody
    public byte[] shoeImage(@PathVariable int imageId) {
        return shoeGoodsService.getById(imageId).getImage();
    }

    @RequestMapping(value = "/imageControllerB/{imageId}")
    @ResponseBody
    public byte[] bookImage(@PathVariable int imageId) {
        return bookGoodsService.getById(imageId).getImage();
    }

}
