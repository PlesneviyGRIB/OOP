package com.savchenko.spring.dao;

import com.savchenko.spring.models.Banner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BannersDAO {

    private List<Banner> banners = new ArrayList<>();
    {
        banners.add(new Banner("Logo","important text", 99.99,null));
        banners.add(new Banner("Transformers","dark side of the moon", 19.00,null));
        banners.add(new Banner("Harry Potter","move about Harry", 215.99,null));
    }

    public List<Banner> getAll(){
        return banners;
    }

    public Banner getById(int id){
        return banners.stream().filter(banner -> banner.getId()==id).findFirst().orElse(null);
    }
}
