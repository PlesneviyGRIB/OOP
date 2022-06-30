package com.savchenko.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bid")
public class CategoriesController {

    @GetMapping("/categories")
    public String categoriesPage(){
        return "categories/categories";
    }
}
