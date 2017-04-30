package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.launchcode.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.Menu;
import org.launchcode.models.forms.AddMenuItemForm;
import java.util.List;
import javax.validation.Valid;

/**
 * Created by ethan on 4/28/17.
 */



@Controller
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;

    private Iterable<Menu> menus;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("menus", menuDao.findAll());
        model.addAttribute("title", "My Menus");

        return "menu/index";

    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayMenuAddForm(Model model){
        Menu menu = new Menu();
        model.addAttribute("title", "Add Menu");
        model.addAttribute("menu", menu);
            return "menu/add";
        }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addMenu(Model model, @ModelAttribute @Valid Menu menu, Errors errors){
        if(errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "menu/add";
        }
        menuDao.save(menu);
        System.out.println(menu.getId());
        return "redirect:view/" + menu.getId();

    }

    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int id){
        Menu menu = menuDao.findOne(id);
        model.addAttribute("menu", menu);
        model.addAttribute("title", menu.getName());
        model.addAttribute("cheeses", menu.getCheeses());
        return "menu/view";
    }

    @RequestMapping(value = "add-item/{id}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int id) {
        Iterable<Cheese> cheeses = cheeseDao.findAll();
        Menu menu = menuDao.findOne(id);
        AddMenuItemForm form = new AddMenuItemForm();
        form.setMenuAndCheeses(menu, cheeses);
        model.addAttribute("title", "Add item to menu: " + menu.getName());
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("menu", menu);
        model.addAttribute("form", form);
        return "menu/add-item";

    }

    @RequestMapping(value = "add-item/{id}", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddMenuItemForm form, Errors errors,
                          @RequestParam int cheeseId, @RequestParam int menuId){
        if(errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "menu/add-items";
        }
        Cheese cheese = cheeseDao.findOne(cheeseId);
        Menu theMenu = menuDao.findOne(menuId);
        theMenu.addItem(cheese);
        menuDao.save(theMenu);

        return "redirect:/menu/view/" + theMenu.getId();

    }


}
