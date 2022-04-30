package com.savchenko.shop.DAO;

import com.savchenko.shop.models.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PurchaseDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PurchaseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Purchase> getByProductId(int id){
        return jdbcTemplate.query("SELECT * FROM purchase WHERE productid=?", new Object[]{id}, new BeanPropertyRowMapper<>(Purchase.class));
    }

    public List<Purchase> getByCustomerId(int id){
        return jdbcTemplate.query("SELECT * FROM purchase WHERE customerid=?", new Object[]{id}, new BeanPropertyRowMapper<>(Purchase.class));
    }

}
