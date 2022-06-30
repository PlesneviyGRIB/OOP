package com.savchenko.spring.dao;

import com.savchenko.spring.models.Category;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoriesDAO {
    private List<Category> categories = new ArrayList<>();
    {
        categories.add(new Category("Music", "music"));
        categories.add(new Category("History", "historical"));
        categories.add(new Category("Sport", "sportsman"));
    }

    public List<Category> getAll(){
        return categories;
    }

    public Category getById(int id){
        return categories.stream().filter(category -> category.getId() == id).findFirst().orElse(null);
    }
}


