package com.savchenko.shop.main.search;

import lombok.Getter;
import lombok.ToString;
import java.util.List;

@Getter
public class Criterias {
    private List<Lastname> criterias;
}

interface Criteria{}

@ToString
@Getter
class Lastname implements Criteria{
    private String lastName;
}

@ToString
@Getter
class ProductCount implements Criteria{
    private String productName;
    private  int minTimes;
}

@ToString
@Getter
class CostOfPurchases implements Criteria{
    private int minExpenses;
    private  int maxExpenses;
}

@ToString
@Getter
class PassiveBuyers implements Criteria {
    private int badCustomers;
}