package MVC.Controller.WebStore.Report;

import MVC.Controller.WelcomeC;
import MVC.Model.Service.WebStore.IWebOrdersService;
import MVC.Model.Service.WebStore.IWebStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by oleg on 11.06.2015.
 */

@Controller
public class GetWebStoreReportC {

    @Autowired
    IWebOrdersService webOrdersService;

    @Autowired
    IWebStoreService webStoreService;

    private int webStoreId;
    List<Object[]> oborotShoe;
    List<Object[]> oborotBook;

    //report web-store main page
    @RequestMapping(value = "/web_store_shoe_reports{id}")
    public ModelAndView webStoreShoeGoodsReport(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("WebStore/Reports/web_store_shoe_reports");

        try {
            if (!WelcomeC.currentUser.getEmail().equals(webStoreService.getById(id).getUsers().getEmail()) || webStoreService.getById(id).getType_id() == 1)
                return new ModelAndView("403Page");
        } catch(NullPointerException e){
            return new ModelAndView("403Page");
        }

        webStoreId = id;
        modelAndView.addObject("username", WelcomeC.username);
        return modelAndView;
    }

    @RequestMapping(value = "/web_store_book_reports{id}")
    public ModelAndView webStoreBookGoodsReport(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("WebStore/Reports/web_store_book_reports");

        try {
            if (!WelcomeC.currentUser.getEmail().equals(webStoreService.getById(id).getUsers().getEmail()) || webStoreService.getById(id).getType_id() == 2)
                return new ModelAndView("403Page");
        } catch(NullPointerException e){
            return new ModelAndView("403Page");
        }

        webStoreId = id;
        modelAndView.addObject("username", WelcomeC.username);
        return modelAndView;
    }

    //report web-store main page
    @RequestMapping(value = "/sstr{id}")
    public @ResponseBody
    ModelAndView webStoreShoeGoodsReportGet(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("WebStore/Reports/web_store_shoe_reports_body");

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("reportId", id);
        return modelAndView;
    }

    //report web-store main page
    @RequestMapping(value = "/bstr{id}")
    public @ResponseBody
    ModelAndView webStoreBookGoodsReportGet(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("WebStore/Reports/web_store_book_reports_body");

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("reportId", id);
        return modelAndView;
    }

    @RequestMapping(value = "/webShoeOborotReport")
    public @ResponseBody
    String oborotShoePost(@RequestParam String beginDate, @RequestParam String endDate){
        oborotShoe=webOrdersService.shoeOborot(webStoreId, beginDate, endDate);
        return "success";
    }

    @RequestMapping(value = "/wsrep1")
    public @ResponseBody
    ModelAndView webShoeOborotGet(){
        ModelAndView modelAndView = new ModelAndView("WebStore/Reports/web_oborot_report");

        modelAndView.addObject("report", oborotShoe);
        return modelAndView;
    }

    @RequestMapping(value = "/webBookOborotReport")
    public @ResponseBody
    String oborotBookPost(@RequestParam String beginDate, @RequestParam String endDate){
        oborotBook=webOrdersService.bookOborot(webStoreId, beginDate, endDate);
        return "success";
    }

    @RequestMapping(value = "/wbrep1")
    public @ResponseBody
    ModelAndView webBookOborotGet(){
        ModelAndView modelAndView = new ModelAndView("WebStore/Reports/web_oborot_report");

        modelAndView.addObject("report", oborotBook);
        return modelAndView;
    }

}
