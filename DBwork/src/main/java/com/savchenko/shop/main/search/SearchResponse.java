package com.savchenko.shop.main.search;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse {
    private final String type = "search";
    private List<CurrentSearchResponse> results = new ArrayList<>();

    public void addCurrentSearchResponse( CurrentSearchResponse currentSearchResponse){
        results.add(currentSearchResponse);
    }
}
