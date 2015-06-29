package MVC.Controller.Store;

import MVC.Controller.WelcomeC;
import MVC.Model.Service.Store.IStoreService;
import MVC.Model.Service.Store.IStoreTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by oleg on 24.05.2015.
 */

@Controller
public class GetStoreListC {

    @Autowired
    IStoreService storeService;

    @Autowired
    IStoreTypeService storeTypeService;

    @RequestMapping("all_stores")
    public ModelAndView getAllStoreTypes(){
        ModelAndView modelAndView = new ModelAndView("Store/all_stores");

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("list_store_types", storeTypeService.getAll());
        return modelAndView;
    }

    //show all stores filter by type
    @RequestMapping("all_stores/{type}")
    public
    @ResponseBody
    ModelAndView getStoreByType(@PathVariable int type) {
        ModelAndView modelAndView = new ModelAndView("Components/store_types_ajax");
        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("stores", storeService.getAllByType(type));
        return modelAndView;
    }
}
