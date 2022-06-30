package com.savchenko.spring.controllers;

import com.savchenko.spring.dao.BannersDAO;
import com.savchenko.spring.models.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bid")
public class BannersController {

    private final BannersDAO bannersDAO;

    @Autowired
    public BannersController(BannersDAO bannersDAO) {
        this.bannersDAO = bannersDAO;
    }

    @GetMapping("/banners")
    public String bannersPage(Model model){
        model.addAttribute("banners", bannersDAO.getAll());
        return "banners/banners";
    }

    @GetMapping("/banners/new")
    public String newBannerPage(Model model){
        model.addAttribute("banners", bannersDAO.getAll());
        return "banners/createBanner";
    }

    @GetMapping("/banners/{id}")
    public String bannerByIdPage(@PathVariable("id") int id, Model model){
        Banner banner = bannersDAO.getById(id);
        if(banner == null) return "redirect:/bid/banners";
        model.addAttribute("banner", banner);
        model.addAttribute("banners", bannersDAO.getAll());
        return "banners/banner";
    }
}
