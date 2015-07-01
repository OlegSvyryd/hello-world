package MVC.Controller.Company.Goods;

import MVC.Controller.WelcomeC;
import MVC.Model.Entity.Company.Goods.CompanyCatalog;
import MVC.Model.Entity.Report.Mailing;
import MVC.Model.Entity.Store.Goods.StoreCatalog;
import MVC.Model.Entity.Store.Store;
import MVC.Model.Service.Company.Goods.ICompanyCatalogService;
import MVC.Model.Service.Store.Goods.IStoreCatalogService;
import MVC.Model.Service.Store.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by oleg on 28.05.2015.
 */

@Controller
public class AddGoodsInMyStoreC {

    @Autowired
    IStoreCatalogService storeCatalogService;

    @Autowired
    ICompanyCatalogService companyCatalogService;

    @Autowired
    IStoreService storeService;

    @Autowired
    JavaMailSender javaMailSender;

    private static int goodsId;

    @RequestMapping("/company_goods_for_order{id}")
    public ModelAndView getGoodsToAddInStore(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("Company/Goods/company_goods_for_order");

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("company_goods", companyCatalogService.getAll(id));

        return modelAndView;
    }

    @RequestMapping("/add_goods_in_my_store{id}")
    public ModelAndView getMyStoresToAddingGoodsGet(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("Store/my_stores_during_adding_goods");

        goodsId = id;
        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("stores", storeService.getAllWithoutThisGoods(WelcomeC.currentUser.getId(), id));

        return modelAndView;
    }

    //ajax add goods in my list stores
    @RequestMapping(value = "add_goods_in_my_store_success", method = RequestMethod.POST)
    public @ResponseBody
    String getMyStoresToAddingGoodsPost(@RequestParam int idStore) {

        Store store = storeService.getById(idStore);
        CompanyCatalog companyCatalog = companyCatalogService.getById(goodsId);

        StoreCatalog storeCatalog = new StoreCatalog();
        storeCatalog.setName(companyCatalog.getName());
        storeCatalog.setPrice(companyCatalog.getPrice());
        storeCatalog.setDimension(companyCatalog.getDimension());
        storeCatalog.setDescription(companyCatalog.getDescription());
        storeCatalog.setStore(store);
        storeCatalog.setCompanyGoodsId(companyCatalog.getId());
        storeCatalog.setCompanyName(companyCatalog.getCompany().getName());
        storeCatalog.setGoodsType(companyCatalog.getGoodsType().getName());


        Mailing mailing = new Mailing();
        mailing.setMailSender(javaMailSender);
        mailing.sendMail("robannnnn@gmail.com",
                companyCatalog.getCompany().getUsers().getEmail(),
                "StoreBase: Користувач зберіг ваш товар",
                "Вітаю! Ваш товар(" + companyCatalog.getName() + ") було додано користувачем '" + WelcomeC.username + "' в свій магазин(" + store.getName() + ").");

                storeCatalogService.insert(storeCatalog);
        return "success";
    }

}
