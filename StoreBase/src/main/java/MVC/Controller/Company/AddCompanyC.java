package MVC.Controller.Company;

import MVC.Controller.WelcomeC;
import MVC.Model.Entity.Company.Company;
import MVC.Model.Entity.Store.Store;
import MVC.Model.Service.Company.ICompanyService;
import MVC.Model.Service.Company.ICompanyTypeService;
import MVC.Model.Service.Store.IStoreService;
import MVC.Model.Service.Store.IStoreTypeService;
import MVC.Model.Service.Users.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by oleg on 23.05.2015.
 */

@Controller
public class AddCompanyC {

    @Autowired
    ICompanyService companyService;

    @Autowired
    ICompanyTypeService companyTypeService;

    @RequestMapping(value = "/add_company", method = RequestMethod.GET)
    public ModelAndView addCompanyGet(){
        ModelAndView modelAndView = new ModelAndView("Company/add_company");

        modelAndView.addObject("company", new Company());
        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("company_types", companyTypeService.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/add_company_success", method = RequestMethod.POST)
    public ModelAndView addCompanyPost(@Valid @ModelAttribute("company") Company company, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:my_companies");

        if(bindingResult.hasErrors()) {
            ModelAndView modelAndView1 = new ModelAndView("Company/add_company");
            modelAndView1.addObject("company", company);
            modelAndView.addObject("company_types", companyTypeService.getAll());
            return modelAndView1;
        }

        company.setUsers(WelcomeC.currentUser);
        companyService.insert(company);
        return modelAndView;
    }
}