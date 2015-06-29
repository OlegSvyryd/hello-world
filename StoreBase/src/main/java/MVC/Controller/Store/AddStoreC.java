package MVC.Controller.Store;

import MVC.Controller.WelcomeC;
import MVC.Model.Entity.Store.Store;
import MVC.Model.Entity.Users.Users;
import MVC.Model.Service.Store.IStoreService;
import MVC.Model.Service.Store.IStoreTypeService;
import MVC.Model.Service.Users.IUsersService;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

/**
 * Created by oleg on 23.05.2015.
 */

@Controller
public class AddStoreC {

    @Autowired
    IStoreService storeService;

    @Autowired
    IStoreTypeService storeTypeService;

    @RequestMapping(value = "/add_store", method = RequestMethod.GET)
    public ModelAndView addStoreGet(){
        ModelAndView modelAndView = new ModelAndView("Store/add_store");

        modelAndView.addObject("store", new Store());
        modelAndView.addObject("username", WelcomeC.username);
        modelAndView.addObject("typeList", storeTypeService.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/add_store_success", method = RequestMethod.POST)
    public ModelAndView addStorePost(@Valid @ModelAttribute("store") Store store, BindingResult bindingResult, Principal principal){
        ModelAndView modelAndView = new ModelAndView("redirect:my_stores");

        if(bindingResult.hasErrors()) {
            ModelAndView modelAndView1 = new ModelAndView("Store/add_store");
            modelAndView1.addObject("store", store);
            modelAndView.addObject("typeList", storeTypeService.getAll());
            return modelAndView1;
        }

        store.setUsers(WelcomeC.currentUser);
        storeService.insert(store);
        return modelAndView;
    }
}

//!!!!!   try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//            store = objectMapper.readValue(obj, Store.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
