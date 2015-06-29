package MVC.Controller.WebStore;

import MVC.Controller.WelcomeC;
import MVC.Model.Entity.WebStore.WebStore;
import MVC.Model.Service.WebStore.IWebStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by oleg on 06.06.2015.
 */

@Controller
public class AddWebStoreC {

    @Autowired
    IWebStoreService webStoreService;

    @RequestMapping("/add_web_store")
    public ModelAndView addWebStoreGet() {
        ModelAndView modelAndView = new ModelAndView("WebStore/add_web_store", "web_store", new WebStore());
        return modelAndView;
    }

    //Post method
    @RequestMapping(value = "/add_web_store_during", method = RequestMethod.POST)
    public ModelAndView processRegistration(@Valid @ModelAttribute("web_store") WebStore webStore, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("redirect:my_web_stores");

        if(bindingResult.hasErrors()){
            ModelAndView modelAndView1 = new ModelAndView("WebStore/add_web_store");
            modelAndView.addObject("web_store", webStore);
            modelAndView.addObject("error_list", "Помилка, будь-ласка введіть коректні дані");
            return modelAndView1;
        }
        else {
            if (urlExist(webStore.getUrl())) {
                ModelAndView modelAndView1 = new ModelAndView("WebStore/add_web_store");
                modelAndView1.addObject("errorLabelUrl", "Помилка, такий інтернет-адрес вже зареєстрований");
                return modelAndView1;
            }
            else {
                webStore.setUsers(WelcomeC.currentUser);
                webStoreService.insert(webStore);
                return modelAndView;
            }
        }
    }

    private boolean urlExist(String url) {
        WebStore webStore = webStoreService.getByURL(url);
        if (webStore != null) {
            return true;
        }
        return false;
    }

}
