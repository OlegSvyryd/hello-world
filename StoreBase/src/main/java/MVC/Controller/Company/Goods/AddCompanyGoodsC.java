package MVC.Controller.Company.Goods;

import MVC.Controller.WelcomeC;
import MVC.Model.Entity.Company.Company;
import MVC.Model.Entity.Company.Goods.CompanyCatalog;
import MVC.Model.Service.Company.Goods.ICompanyCatalogService;
import MVC.Model.Service.Company.Goods.IGoodsTypeService;
import MVC.Model.Service.Company.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by oleg on 27.05.2015.
 */

@Controller
public class AddCompanyGoodsC {

    @Autowired
    ICompanyService companyService;

    @Autowired
    ICompanyCatalogService companyCatalogService;

    @Autowired
    IGoodsTypeService goodsTypeService;

    private int companyId;

    //add goods
    @RequestMapping(value = "/add_company_goods{id}", method = RequestMethod.GET)
    public ModelAndView addGoodsInCompany(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("Company/Goods/add_company_goods", "companyGoods", new CompanyCatalog());

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("companyId", id);
        companyId = id;
        modelAndView.addObject("goodsType", goodsTypeService.getAll());
        return modelAndView;
    }

    //post method add goods
    @RequestMapping(value = "/add_company_goods_success", method = RequestMethod.POST)
    public ModelAndView addGoodsInCompanyPost(@Valid @ModelAttribute("companyGoods")CompanyCatalog companyCatalog) {
        ModelAndView modelAndView = new ModelAndView("redirect:company_goods" + companyId);

        Company company = companyService.getById(companyId);
        companyCatalog.setCompany(company);

        companyCatalogService.insert(companyCatalog);
        return modelAndView;
    }

}
