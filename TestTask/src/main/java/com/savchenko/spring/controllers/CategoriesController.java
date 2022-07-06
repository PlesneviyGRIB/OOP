package com.savchenko.spring.controllers;

import com.savchenko.spring.DAO.CategoriesDAO;
import com.savchenko.spring.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/bid/categories")
public class CategoriesController {

    private final CategoriesDAO categoriesDAO;

    @Autowired
    public CategoriesController(CategoriesDAO categoriesDAO) {
        this.categoriesDAO = categoriesDAO;
    }

    @GetMapping()
    public String banners(@RequestParam(value = "search", required = false) String search,
                          Model model){
        model.addAttribute("res", Objects.requireNonNullElse(search,""));
        model.addAttribute("categories", categoriesDAO.getAll(search));
        return "categories/categories";
    }

    @GetMapping("/new")
    public String newBanner(Model model){
        model.addAttribute(new Category());
        model.addAttribute("categories", categoriesDAO.getAll(null));
        return "categories/createCategory";
    }

    @PostMapping()
    public String createBanner(@ModelAttribute("category") @Valid Category category,
                               BindingResult bindingResult,
                               Model model){
        if(categoriesDAO.hasCollision(category)) {
            bindingResult.addError(new FieldError(bindingResult.getObjectName(), "name", "\"Name\" and \"RequestID\" should be unique, category with such Name or RequestId already exist!"));
            bindingResult.addError(new FieldError(bindingResult.getObjectName(), "requestId", "\"Name\" and \"RequestID\" should be unique, category with such Name or RequestId already exist!"));
        }
        if(bindingResult.hasErrors()) {
            model.addAttribute("categories",  categoriesDAO.getAll(null));
            return "categories/createCategory";
        }
        categoriesDAO.add(category);
        return "redirect:/bid/categories/" + category.getId();
    }

    @GetMapping("/{id}")
    public String bannerById(@PathVariable("id") int id, Model model){
        Category category = categoriesDAO.getById(id);
        if(category == null) return "redirect:/bid/categories";
        model.addAttribute(category);
        model.addAttribute("categories", categoriesDAO.getAll(null));
        return "categories/category";
    }

    @GetMapping("/{id}/edit")
    public String editBanner(Model model, @PathVariable("id") int id){
        Category category = categoriesDAO.getById(id);
        if(category == null) return "redirect:/bid/categories";
        model.addAttribute(category);
        model.addAttribute("categories", categoriesDAO.getAll(null));
        return "categories/editCategory";
    }

    @PatchMapping("/{id}")
    public String updateBanner(@ModelAttribute("category") @Valid Category category,
                               BindingResult bindingResult,
                               @PathVariable("id") int id,
                               Model model){
        if(categoriesDAO.hasCollision(category)) {
            bindingResult.addError(new FieldError(bindingResult.getObjectName(), "name", "\"Name\" and \"RequestID\" should be unique, category with such Name or RequestId already exist!"));
            bindingResult.addError(new FieldError(bindingResult.getObjectName(), "requestId", "\"Name\" and \"RequestID\" should be unique, category with such Name or RequestId already exist!"));
        }
        if(bindingResult.hasErrors()) {
            model.addAttribute("categories",  categoriesDAO.getAll(null));
            return "categories/editCategory";
        }
        categoriesDAO.update(id, category);
        return "redirect:/bid/categories/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteBanner(@PathVariable("id") int id,
                               Model model){
        if(!categoriesDAO.delete(id)) {
            model.addAttribute("error", "Category can not be delete due to there is(are) linked banner(s)!");
            return "redirect:/bid/categories/" + id;
        }
        return "redirect:/bid/categories/";
    }
}
