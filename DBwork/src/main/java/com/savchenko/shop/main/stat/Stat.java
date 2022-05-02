package com.savchenko.shop.main.stat;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.savchenko.shop.main.supportive.OperationError;
import com.savchenko.shop.main.supportive.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Component
public class Stat implements OperationType {
    @Autowired
    private StatRequest statRequest;
    private File out;

    @Override
    public void makeResponse(File in, File out) {
        this.out = out;
        writeOutputJson(statResponse(readInputJson(in)));
    }

    private StatResponse statResponse(Period period){
        StatResponse statResponse = new StatResponse();
        double totalCost = 0;
        for(CustomerData customerData: statRequest.getDataForeachCustomer(period)){
            totalCost += customerData.getTotalExpenses();
            statResponse.addCustomerData(customerData);
        }
        statResponse.setTotalExpenses(totalCost);
        if(statResponse.getCustomers().size() > 0) statResponse.setAvgExpenses(totalCost / statResponse.getCustomers().size());
        else statResponse.setAvgExpenses(0.0);
        statResponse.setTotalDays((int)ChronoUnit.DAYS.between(period.getFirstDate(), period.getSecondDate()) + 1);

        return statResponse;
    }

    private Period readInputJson(File in) {
        Gson gson = new Gson();
        Period period = new Period();
        try(FileReader fileReader = new FileReader(in)) {
            Map<?,?> map = gson.fromJson(fileReader, LinkedTreeMap.class);
            period.setFirstDate(LocalDate.parse(map.get("startDate").toString()));
            period.setSecondDate(LocalDate.parse(map.get("endDate").toString()));
        } catch (Exception e){
            writeOutputJson(new OperationError("Wrong date format"));
            System.exit(0);
        }
        return period;
    }

    private void writeOutputJson(StatResponse statResponse){
        Gson gson = new Gson();
        try(FileWriter fileWriter = new FileWriter(out)) {
            fileWriter.append(gson.toJson(statResponse));
        } catch (IOException e){ e.printStackTrace(); }
    }

    private void writeOutputJson(OperationError operationError){
        Gson gson = new Gson();
        try(FileWriter fileWriter = new FileWriter(out)) {
            fileWriter.append(gson.toJson(operationError));
        } catch (IOException e){ e.printStackTrace(); }
    }
}
