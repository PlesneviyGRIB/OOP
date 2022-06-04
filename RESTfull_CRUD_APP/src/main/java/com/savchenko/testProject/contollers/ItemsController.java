package com.savchenko.testProject.contollers;

import com.savchenko.testProject.dao.ItemsDAO;
import com.savchenko.testProject.models.Item;
import com.savchenko.testProject.supportive.SearchItemParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@Api("Controller for REST mapping")
@Controller
@RequestMapping("/items")
public class ItemsController {

    private ItemsDAO itemsDAO;

    @Autowired
    public ItemsController(ItemsDAO itemsDAO) {
        this.itemsDAO = itemsDAO;
    }

    //@ApiOperation("Display method for all elements")
    @GetMapping()
    public String showAll(Model model){
        model.addAttribute("items", itemsDAO.showAll());
        model.addAttribute("search", new SearchItemParams());
        return "items/showAll.html";
    }

    //@ApiOperation("Display method for element with current id")
    @GetMapping("/{id}")
    public String showItem(Model model, @PathVariable("id") int id){
        Item item = itemsDAO.showItem(id);
        if(item == null) return "redirect:/items";
        model.addAttribute("item", item);
        return "items/showItem.html";
    }

    //@ApiOperation("Method for switching to the form of creating a new object")
    @GetMapping("/new")
    public String newItem(@ModelAttribute("item") Item item){
        return "items/new.html";
    }

    //@ApiOperation("method for creating an object in the database with object validation")
    @PostMapping()
    public String create(@ModelAttribute("item") @Valid Item item, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "items/new.html";
        itemsDAO.addItem(item);
        return "redirect:/items";
    }

    //@ApiOperation("Method for switching to the form of editing a new object")
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        Item item = itemsDAO.showItem(id);
        if(item == null) return "redirect:/items";
        model.addAttribute("item", itemsDAO.showItem(id));
        return "items/edit.html";
    }

    //@ApiOperation("method for update an object in the database with object validation")
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("item") @Valid Item item, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()) return "items/edit.html";
        itemsDAO.update(id, item);
        return "redirect:/items";
    }

    //@ApiOperation("method for deleting an object by id")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        itemsDAO.delete(id);
        return "redirect:/items";
    }

    //@ApiOperation("method to display selected filter and filtered list")
    @GetMapping("/filter")
    public String filter(@ModelAttribute("search") SearchItemParams searchItemParams, Model model){
        model.addAttribute("search", searchItemParams);
        model.addAttribute("items", itemsDAO.showAll().stream().filter(searchItemParams::match).toList());
        return "items/filter.html";
    }
}
