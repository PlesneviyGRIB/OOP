package com.savchenko.shop.DAO;

import com.savchenko.shop.models.Customer;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@ToString
public class CustomerDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Customer getById(int id){
        return jdbcTemplate.query("SELECT * FROM customer WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Customer.class)).stream().findAny().orElse(null);
    }

    public List<Customer> getAll(){
        return jdbcTemplate.query("SELECT * FROM customer", new BeanPropertyRowMapper<>(Customer.class));
    }

    public List<Customer> getByLastname(String lastname){
        return jdbcTemplate.query("SELECT * FROM customer WHERE lastname=?", new Object[]{lastname}, new BeanPropertyRowMapper<>(Customer.class));
    }
}
