package MVC.Controller.WebStore;

import MVC.Controller.WelcomeC;
import MVC.Model.Service.WebStore.IWebStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by oleg on 06.06.2015.
 */

@Controller
public class GetListWebStoresC {

    @Autowired
    IWebStoreService webStoreService;

    @RequestMapping("all_web_stores")
    public ModelAndView getAllWebStoreTypes(){
        ModelAndView modelAndView = new ModelAndView("WebStore/all_web_stores");

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("list_web_store_types", webStoreService.getAll());
        return modelAndView;
    }

    //show all web - stores filter by type
    @RequestMapping("all_web_stores/{type}")
    public
    @ResponseBody
    ModelAndView getWebStoreByType(@PathVariable int type) {
        ModelAndView modelAndView = new ModelAndView("Components/web_store_types_ajax");
        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("web_stores", webStoreService.getAllByType(type));
        return modelAndView;
    }

}
