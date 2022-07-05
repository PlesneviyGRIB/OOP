package com.savchenko.spring.controllers;

import com.savchenko.spring.DAO.CategoriesDAO;
import com.savchenko.spring.models.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.savchenko.spring.DAO.BannersDAO;
import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/bid/banners")
@Validated
public class BannersController {

    private final BannersDAO bannersDAO;
    private final CategoriesDAO categoriesDAO;

    @Autowired
    public BannersController(BannersDAO bannersDAO, CategoriesDAO categoriesDAO) {
        this.bannersDAO = bannersDAO;
        this.categoriesDAO = categoriesDAO;
    }

    @GetMapping()
    public String banners(@RequestParam(value = "search", required = false) String search,
                          Model model){
        model.addAttribute("res", Objects.requireNonNullElse(search, ""));
        model.addAttribute("banners", bannersDAO.getAll(search));
        return "banners/banners";
    }


    @GetMapping("/new")
    public String newBanner(Model model){
        model.addAttribute(new Banner());
        model.addAttribute("banners", bannersDAO.getAll(null));
        model.addAttribute("categories", categoriesDAO.getAll(null));
        return "banners/createBanner";
    }

    @PostMapping()
    public String createBanner(@ModelAttribute("banner") @Valid Banner banner,
                               BindingResult bindingResult,
                               Model model){
        if(bannersDAO.hasNameCollision(banner)) bindingResult.addError(new FieldError(bindingResult.getObjectName(), "name", "Banner with such name already exist!"));
        if(bindingResult.hasErrors()) {
            model.addAttribute("banners", bannersDAO.getAll(null));
            model.addAttribute("categories", categoriesDAO.getAll(null));
            return "banners/createBanner";
        }
        bannersDAO.add(banner);
        return "redirect:/bid/banners/" + banner.getId();
    }

    @GetMapping("/{id}")
    public String bannerById(@PathVariable("id") int id,
                             Model model){
        Banner banner = bannersDAO.getById(id);
        if(banner == null) return "redirect:/bid/banners";
        model.addAttribute("banner", banner);
        model.addAttribute("banners", bannersDAO.getAll(null));
        return "banners/banner";
    }

    @GetMapping("/{id}/edit")
    public String editBanner(@PathVariable("id") int id,
                             Model model){
        Banner banner = bannersDAO.getById(id);
        if(banner == null) return "redirect:/bid/banners";
        model.addAttribute(banner);
        model.addAttribute("banners", bannersDAO.getAll(null));
        model.addAttribute("categories", categoriesDAO.getAll(null));
        return "banners/editBanner";
    }

    @PatchMapping("/{id}")
    public String updateBanner(@ModelAttribute("banner") @Valid Banner banner,
                               BindingResult bindingResult,
                               @PathVariable("id") int id,
                               Model model){
        if(bannersDAO.hasNameCollision(banner)) bindingResult.addError(new FieldError(bindingResult.getObjectName(), "name", "Banner with such name already exist!"));
        if(bindingResult.hasErrors()) {
            model.addAttribute("banners", bannersDAO.getAll(null));
            model.addAttribute("categories", categoriesDAO.getAll(null));
            return "banners/editBanner";
        }
        bannersDAO.update(id, banner);
        return "redirect:/bid/banners/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteBanner(@PathVariable("id") int id){
        bannersDAO.delete(id);
        return "redirect:/bid/banners/";
    }
}
