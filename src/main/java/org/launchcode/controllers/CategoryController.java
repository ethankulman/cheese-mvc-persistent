package org.launchcode.controllers;
import org.launchcode.models.Category;
import org.launchcode.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import javax.validation.Valid;

/**
 * Created by ethan on 4/6/17.
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    private Iterable<Category> categories;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model){
        categories = categoryDao.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("title", "Cheese Categories");
        return "category/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("category", new Category());
        model.addAttribute("title", "Add Category");
        return "category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String postCategory(Model model, @ModelAttribute @Valid Category category, Errors errors) {
        if(errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "category/add";
        }
        categoryDao.save(category);
        return "redirect:";

    }
}
