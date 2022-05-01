package com.savchenko.shop.main.stat;

import com.savchenko.shop.DAO.CustomerDAO;
import com.savchenko.shop.DAO.ProductDAO;
import com.savchenko.shop.DAO.PurchaseDAO;
import com.savchenko.shop.models.Customer;
import com.savchenko.shop.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class StatRequest {
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private PurchaseDAO purchaseDAO;

    public List<CustomerData> getDataForeachCustomer(Period period){
        List<CustomerData> customersData = new ArrayList<>();
        customerDAO.getAll().forEach(customer -> customersData.add(getCustomerData(customer, period)));
        return customersData;
    }

    private CustomerData getCustomerData(Customer customer, Period period){
        CustomerData customerData = new CustomerData();
        customerData.setName(customer.getLastName() + " " + customer.getName());

        List<Product> products = new ArrayList<>();
        purchaseDAO.getByIdWithPeriod(customer.getId(), period.getFirstDate(),period.getSecondDate()).forEach(purchase -> {
            products.add(productDAO.getById(purchase.getProductId()));
        });
        double totalCost = 0;
        for(Product product: products) totalCost += product.getExpenses();
        customerData.setTotalExpenses(totalCost);
        customerData.setPurchases(products);

        return customerData;
    }
}
