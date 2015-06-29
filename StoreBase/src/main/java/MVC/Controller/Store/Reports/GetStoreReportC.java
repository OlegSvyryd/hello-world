package MVC.Controller.Store.Reports;

import MVC.Controller.WelcomeC;
import MVC.Model.Service.Report.IOrdersService;
import MVC.Model.Service.Store.IStoreService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by oleg on 28.05.2015.
 */

@Controller
public class GetStoreReportC {

    @Autowired
    IOrdersService ordersService;

    @Autowired
    IStoreService storeService;

    private int storeId;
    List<Object[]> oborot;
    List<Object[]> pruhidTovary;
    List<Object[]> prubytok;
    List<Object[]> rentabelnist;

    //report store main page
    @RequestMapping(value = "/store_reports{id}")
    public ModelAndView storeGoodsReport(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("Store/Reports/store_reports");

        try {
            if (!WelcomeC.currentUser.getEmail().equals(storeService.getById(id).getUsers().getEmail()))
            return new ModelAndView("403Page");
        } catch(NullPointerException e){
            return new ModelAndView("403Page");
        }

        storeId = id;
        modelAndView.addObject("username", WelcomeC.username);
        return modelAndView;
    }

    //report store main page
    @RequestMapping(value = "/str{id}")
    public @ResponseBody
    ModelAndView storeGoodsReportGet(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("Store/Reports/store_reports_body");

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("reportId", id);
        return modelAndView;
    }

    @RequestMapping(value = "/oborotReport")
    public @ResponseBody
    String oborotPost(@RequestParam String beginDate, @RequestParam String endDate){
        oborot=ordersService.oborot(storeId, beginDate, endDate);
        return "success";
    }

    @RequestMapping(value = "/rep1")
    public @ResponseBody
    ModelAndView oborotGet(){
        ModelAndView modelAndView = new ModelAndView("Store/Reports/oborot_report");

        modelAndView.addObject("report", oborot);
        return modelAndView;
    }

    @RequestMapping(value = "/pruhidTovaryReport")
    public @ResponseBody
    String pruhidTovaryPost(@RequestParam String beginDate, @RequestParam String endDate){
        pruhidTovary=ordersService.pruhidTovary(storeId, beginDate, endDate);
        return "success";
    }

    @RequestMapping(value = "/rep2")
    public @ResponseBody
    ModelAndView pruhidTovaryGet(){
        ModelAndView modelAndView = new ModelAndView("Store/Reports/pruhid_tovary_report");

        modelAndView.addObject("report", pruhidTovary);
        return modelAndView;
    }

    @RequestMapping(value = "/prubytokReport")
    public @ResponseBody
    String prubytokPost(@RequestParam String beginDate, @RequestParam String endDate){
        prubytok=ordersService.prubytok(storeId, beginDate, endDate);
        return "success";
    }

    @RequestMapping(value = "/rep3")
    public @ResponseBody
    ModelAndView prubytokGet(){
        ModelAndView modelAndView = new ModelAndView("Store/Reports/prubytok_report");

        modelAndView.addObject("report", prubytok);
        return modelAndView;
    }

    @RequestMapping(value = "/rentabelnistReport")
    public @ResponseBody
    String rentabelnistPost(@RequestParam String beginDate, @RequestParam String endDate){
        rentabelnist=ordersService.rentabelnistj(storeId, beginDate, endDate);
        return "success";
    }

    @RequestMapping(value = "/rep4")
    public @ResponseBody
    ModelAndView rentabelnistGet(){
        ModelAndView modelAndView = new ModelAndView("Store/Reports/rentabelnist_report");

        modelAndView.addObject("report", rentabelnist);
        return modelAndView;
    }

}

