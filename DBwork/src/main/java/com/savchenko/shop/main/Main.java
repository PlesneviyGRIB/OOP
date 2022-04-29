package com.savchenko.shop.main;

import com.savchenko.shop.DAO.CustomerDAO;
import com.savchenko.shop.config.SpringConfig;
import com.savchenko.shop.main.search.Search;
import com.savchenko.shop.main.stat.Stat;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if(args.length < 3) {
            System.out.println("Wrong usage! Please provide operation type, path of input file and path of output file.");
            System.exit(0);
        }

        String type = args[0];
        File inFile = new File(args[1]),
                outFile = new File(args[2]);

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

        switch (type.toLowerCase()){
            case "search": {
                new Search(inFile, outFile, (CustomerDAO) applicationContext.getBean("customerDAO"));
                break;
            }
            case "stat": {
                new Stat(inFile, outFile);
                break;
            }
            default: System.out.println("Wrong operation type! Available types: search, stat");
        }
    }
}
