package MVC.Controller.Company;

import MVC.Controller.WelcomeC;
import MVC.Model.Service.Company.ICompanyService;
import MVC.Model.Service.Company.ICompanyTypeService;
import MVC.Model.Service.Store.IStoreService;
import MVC.Model.Service.Store.IStoreTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by oleg on 24.05.2015.
 */

@Controller
public class GetCompanyListC {

    @Autowired
    ICompanyService companyService;

    @Autowired
    ICompanyTypeService companyTypeService;

    @RequestMapping("all_companies")
    public ModelAndView getAllCompanyTypes(){
        ModelAndView modelAndView = new ModelAndView("Company/all_companies");

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("list_company_types", companyTypeService.getAll());
        return modelAndView;
    }

    //show all companies filter by type
    @RequestMapping("all_companies/{type}")
    public
    @ResponseBody
    ModelAndView getCompanyByType(@PathVariable int type) {
        ModelAndView modelAndView = new ModelAndView("Components/company_types_ajax");
        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("companies", companyService.getAllByType(type));
        return modelAndView;
    }
}
