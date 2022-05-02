package com.savchenko.shop.main.search;

import com.google.gson.Gson;
import com.savchenko.shop.main.supportive.OperationError;
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
    private File out;
    private Gson gson = new Gson();

    @Override
    public void makeResponse(File in, File out){
        this.out = out;
        writeOutputJson(searchResponse(readInputJson(in)));
    }

    private Criterias readInputJson(File in) {
        Criterias criterias = null;
        try(FileReader fileReader = new FileReader(in)) {
            criterias = gson.fromJson(fileReader, Criterias.class);
            if(criterias.getCriterias() == null){
                writeOutputJson(new OperationError("Wrong input json syntax for search type"));
                System.exit(0);
            }
        } catch (IOException e){
            writeOutputJson(new OperationError("Problem with input file"));
            System.exit(0);
        }
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

    private void writeOutputJson(SearchResponse searchResponse){
        try(FileWriter fileWriter = new FileWriter(out)) {
            fileWriter.append(gson.toJson(searchResponse));
        } catch (IOException e){ e.printStackTrace(); }
    }

    private void writeOutputJson(OperationError operationError){
        try(FileWriter fileWriter = new FileWriter(out)) {
            fileWriter.append(gson.toJson(operationError));
        } catch (IOException e){ e.printStackTrace(); }
    }
}
