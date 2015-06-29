package MVC.Controller.WebStore;

import MVC.Controller.WelcomeC;
import MVC.Model.Entity.Store.Store;
import MVC.Model.Entity.WebStore.WebStore;
import MVC.Model.Service.Store.IStoreService;
import MVC.Model.Service.Store.IStoreTypeService;
import MVC.Model.Service.WebStore.IWebStoreService;
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
public class MyWebStoresC {

    @Autowired
    IWebStoreService webStoreService;

    @RequestMapping("my_web_stores")
    public ModelAndView getMyStores(){
        ModelAndView modelAndView = new ModelAndView("WebStore/my_web_stores");

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("web_stores", webStoreService.getAll(WelcomeC.currentUser.getId()));
        return modelAndView;
    }

    @RequestMapping(value = "delete_web_store", method = RequestMethod.POST)
    public
    @ResponseBody
    String deleteStore(@RequestParam int idWebStore){
        webStoreService.delete(webStoreService.getById(idWebStore));
        return "success";
    }

    @RequestMapping(value = "update_web_store{id}", method = RequestMethod.GET)
    public ModelAndView updateStoreGet(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("WebStore/update_web_store");

        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("web_store", webStoreService.getById(id));
        return modelAndView;
    }

    @RequestMapping(value = "upd_web_store_success", method = RequestMethod.POST)
    public ModelAndView updateStorePost(@Valid @ModelAttribute("web_store")WebStore webStore, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:my_web_stores");

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("web_store", webStore);
            return new ModelAndView("WebStore/update_web_store");
        }

        webStore.setUsers(WelcomeC.currentUser);
        webStoreService.update(webStore);
        return modelAndView;
    }
}
