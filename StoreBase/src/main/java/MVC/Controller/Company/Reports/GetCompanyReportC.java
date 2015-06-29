package MVC.Controller.Company.Reports;

import MVC.Controller.WelcomeC;
import MVC.Model.Service.Company.ICompanyService;
import MVC.Model.Service.Report.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by oleg on 28.05.2015.
 */

@Controller
public class GetCompanyReportC {

    @Autowired
    IOrdersService ordersService;

    @Autowired
    ICompanyService companyService;

    private int companyId;
    List<Object[]> oborot;
    List<Object[]> storeStat;

    //report company main page
    @RequestMapping(value = "/company_reports{id}")
    public ModelAndView companyGoodsReport(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("Company/Reports/company_reports");

        try {
            if (!WelcomeC.currentUser.getEmail().equals(companyService.getById(id).getUsers().getEmail()))
                return new ModelAndView("403Page");
        } catch(NullPointerException e){
            return new ModelAndView("403Page");
        }

        companyId = id;
        modelAndView.addObject("username", WelcomeC.username);
        return modelAndView;
    }

    //report company main page
    @RequestMapping(value = "/cmr{id}")
    public @ResponseBody
    ModelAndView companyGoodsReportGet(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("Company/Reports/company_reports_body");

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("reportId", id);
        return modelAndView;
    }

    @RequestMapping(value = "/cOborotReport")
    public @ResponseBody
    String oborotPost(@RequestParam String beginDate, @RequestParam String endDate){
        oborot=ordersService.cOborot(companyId, beginDate, endDate);
        return "success";
    }

    @RequestMapping(value = "/crep1")
    public @ResponseBody
    ModelAndView oborotGet(){
        ModelAndView modelAndView = new ModelAndView("Company/Reports/c_oborot_report");

        modelAndView.addObject("report", oborot);
        return modelAndView;
    }

    @RequestMapping(value = "/cmpStoreStat")
    public @ResponseBody
    String storeStatPost(@RequestParam String beginDate, @RequestParam String endDate){
        storeStat=ordersService.cMinStatistic(companyId, beginDate, endDate);
        return "success";
    }

    @RequestMapping(value = "/crep2")
    public @ResponseBody
    ModelAndView storeStatGet(){
        ModelAndView modelAndView = new ModelAndView("Company/Reports/c_min_store_stat_report");

        modelAndView.addObject("report", storeStat);
        return modelAndView;
    }

}

