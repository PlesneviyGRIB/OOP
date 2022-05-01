package com.savchenko.shop.DAO;

import com.savchenko.shop.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> getByTitle(String title){
        return jdbcTemplate.query("SELECT * FROM product WHERE title=?", new Object[]{title}, new BeanPropertyRowMapper<>(Product.class));
    }

    public Product getById(int id){
        return jdbcTemplate.query("SELECT * FROM product WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Product.class)).stream().findAny().orElse(null);
    }
}
