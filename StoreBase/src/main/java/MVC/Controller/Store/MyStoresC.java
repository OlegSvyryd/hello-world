package MVC.Controller.Store;

import MVC.Controller.WelcomeC;
import MVC.Model.Entity.Store.Store;
import MVC.Model.Service.Store.IStoreService;
import MVC.Model.Service.Store.IStoreTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by oleg on 25.05.2015.
 */

@Controller
public class MyStoresC {

    @Autowired
    IStoreService storeService;

    @Autowired
    IStoreTypeService storeTypeService;

    @RequestMapping("my_stores")
    public ModelAndView getMyStores(){
        ModelAndView modelAndView = new ModelAndView("Store/my_stores");

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("stores", storeService.getAll(WelcomeC.currentUser.getId()));
        return modelAndView;
    }

    @RequestMapping(value = "delete_store", method = RequestMethod.POST)
    public
    @ResponseBody
    String deleteStore(@RequestParam int idStore){
        storeService.delete(storeService.getById(idStore));
        return "success";
    }

    @RequestMapping(value = "update_store{id}", method = RequestMethod.GET)
    public ModelAndView updateStoreGet(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("Store/update_store");

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("store", storeService.getById(id));
        modelAndView.addObject("store_types", storeTypeService.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "update_store_success", method = RequestMethod.POST)
    public ModelAndView updateStorePost(@Valid @ModelAttribute("store") Store store, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:my_stores");

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("store", store);
            return new ModelAndView("Store/update_store");
        }

        System.out.println(store);
        store.setUsers(WelcomeC.currentUser);
        storeService.update(store);
        return modelAndView;
    }
}
