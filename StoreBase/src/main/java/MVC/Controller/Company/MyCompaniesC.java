package MVC.Controller.Company;

import MVC.Controller.WelcomeC;
import MVC.Model.Entity.Company.Company;
import MVC.Model.Entity.Store.Store;
import MVC.Model.Service.Company.ICompanyService;
import MVC.Model.Service.Company.ICompanyTypeService;
import MVC.Model.Service.Store.IStoreService;
import MVC.Model.Service.Store.IStoreTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by oleg on 25.05.2015.
 */

@Controller
public class MyCompaniesC {

    @Autowired
    ICompanyService companyService;

    @Autowired
    ICompanyTypeService companyTypeService;

    @RequestMapping("my_companies")
    public ModelAndView getMyCompanies(){
        ModelAndView modelAndView = new ModelAndView("Company/my_companies");

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("companies", companyService.getAll(WelcomeC.currentUser.getId()));
        return modelAndView;
    }

    @RequestMapping(value = "delete_company", method = RequestMethod.POST)
    public
    @ResponseBody
    String deleteCompany(@RequestParam int idCompany){
        companyService.delete(companyService.getById(idCompany));
        return "success";
    }

    @RequestMapping(value = "update_company{id}", method = RequestMethod.GET)
    public ModelAndView updateCompanyGet(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("Company/update_company");

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("company", companyService.getById(id));
        modelAndView.addObject("company_types", companyTypeService.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "update_company_success", method = RequestMethod.POST)
    public ModelAndView updateCompanyPost(@Valid @ModelAttribute("company") Company company, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:my_companies");

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("company", company);
            return new ModelAndView("Company/update_company");
        }

        company.setUsers(WelcomeC.currentUser);
        companyService.update(company);
        return modelAndView;
    }
}
