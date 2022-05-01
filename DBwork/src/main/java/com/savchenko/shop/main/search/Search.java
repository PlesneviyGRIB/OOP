package com.savchenko.shop.main.search;

import com.google.gson.Gson;
import com.savchenko.shop.main.supportive.OperationType;
import com.savchenko.shop.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class Search implements OperationType {
    @Autowired
    private SearchRequest request;

    @Override
    public void makeResponse(File in, File out){
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
        criterias.getCriterias().forEach(criteria -> searchResponse.addCurrentSearchResponse(currentResponse((Map<?, ?>) criteria)));
        return searchResponse;
    }

    private CurrentSearchResponse currentResponse(Map<?, ?> map){
        CurrentSearchResponse searchResponse = new CurrentSearchResponse(map);
        List<Customer> customers = new ArrayList<>();
        if(map.containsKey("lastName")) customers = request.lastName((String) map.get("lastName"));
        if(map.containsKey("productName")) customers = request.titleAndCount((String) map.get("productName"), (double) map.get("minTimes"));
        if(map.containsKey("minExpenses")) customers = request.minMaxCost((double) map.get("minExpenses"), (double) map.get("maxExpenses"));
        if(map.containsKey("badCustomers")) customers = request.passiveCustomers((double) map.get("badCustomers"));
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
