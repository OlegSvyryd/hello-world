package MVC.Controller.Company.Goods;

import MVC.Controller.WelcomeC;
import MVC.Model.Entity.Company.Goods.CompanyCatalog;
import MVC.Model.Service.Company.Goods.ICompanyCatalogService;
import MVC.Model.Service.Company.ICompanyService;
import MVC.Model.Service.Report.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by oleg on 26.05.2015.
 */

@Controller
public class MyCompanyCatalogC {

    @Autowired
    ICompanyCatalogService companyCatalogService;

    @Autowired
    IOrdersService ordersService;

    @Autowired
    ICompanyService companyService;

    //catalog goods
    @RequestMapping(value = "/company_goods{id}")
    public ModelAndView companyGoods(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("Company/Goods/company_goods");

        try {
            if (!WelcomeC.currentUser.getEmail().equals(companyService.getById(id).getUsers().getEmail()))
                return new ModelAndView("403Page");
        } catch(NullPointerException e){
            return new ModelAndView("403Page");
        }

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("companyId", id);
        modelAndView.addObject("company_goods", companyCatalogService.getAll(id, WelcomeC.currentUser.getEmail()));
        return modelAndView;
    }

    //ajax delete goods
    @RequestMapping(value = "del_company_goods_success", method = RequestMethod.POST)
    public @ResponseBody
    String delCompanyGoodsPost(@RequestParam int idGoods) {
        System.out.println(idGoods);
        companyCatalogService.delete(companyCatalogService.getById(idGoods));
        return "success";
    }

    //update goods
    @RequestMapping(value = "/upd_company_goods{id}", method = RequestMethod.GET)
    public ModelAndView updGoods(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("Company/Goods/update_company_goods");

        try {
            modelAndView.addObject("company_goods", companyCatalogService.getById(id));
            modelAndView.addObject("username", WelcomeC.username);
            return modelAndView;
        }
        catch(Exception e){
            return new ModelAndView("redirect:my_company?error");
        }
    }

    //send post update goods
    @RequestMapping(value = "/upd_company_goods_success", method = RequestMethod.POST)
    public ModelAndView updGoodsPost(@Valid @ModelAttribute("company_goods") CompanyCatalog companyCatalog) {
        ModelAndView modelAndView = new ModelAndView("redirect:company_goods" + companyCatalog.getCompany().getId());

        companyCatalogService.update(companyCatalog);
        return modelAndView;
    }
}
