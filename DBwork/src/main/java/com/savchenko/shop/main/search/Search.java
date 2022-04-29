package com.savchenko.shop.main.search;

import com.google.gson.Gson;
import com.savchenko.shop.DAO.CustomerDAO;
import com.savchenko.shop.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Search {
    private final CustomerDAO customerDAO;

    public Search(File in, File out, CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
        writeOutputJson(searchResponse(readInputJson(in)), out);
    }

    private Criterias readInputJson(File in) {
        Gson gson = new Gson();
        Criterias criterias = null;
        try(FileReader fileReader = new FileReader(in)) {
            criterias = gson.fromJson(fileReader, Criterias.class);
        } catch (IOException e){ e.printStackTrace(); }
        return criterias;
    }

    private SearchResponse searchResponse(Criterias criterias){
        SearchResponse searchResponse = new SearchResponse();
        criterias.getCriterias().forEach(criteria -> searchResponse.addCurrentSearchResponse(currentResponse(criteria)));
        return searchResponse;
    }

    private CurrentSearchResponse currentResponse(Criteria criteria){
        CurrentSearchResponse searchResponse = new CurrentSearchResponse(criteria);
        List<Customer> customers = customerDAO.getByLastname(((Lastname)criteria).getLastName());
        customers.forEach(searchResponse::addCustomer);
        return searchResponse;
    }

    private void writeOutputJson(SearchResponse searchResponse, File out){
        Gson gson = new Gson();
        try(FileWriter fileWriter = new FileWriter(out)) {
            fileWriter.append(gson.toJson(searchResponse));
        } catch (IOException e){ e.printStackTrace(); }
    }
}
