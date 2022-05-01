package com.savchenko.shop.main;

import com.savchenko.shop.config.SpringConfig;
import com.savchenko.shop.main.search.Search;
import com.savchenko.shop.main.stat.Stat;
import com.savchenko.shop.main.supportive.OperationType;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        if(args.length < 3) {
            System.out.println("Wrong usage! Please provide [1]operation type, [2]path of input file and [3]path of output file.");
            System.exit(0);
        }

        String type = args[0];
        File inFile = new File(args[1]),
                outFile = new File(args[2]);

        if(!inFile.exists()){
            System.out.println("Inappropriate file path! " + args[1]);
            System.exit(0);
        }

        if(!outFile.exists()){
            System.out.println("Inappropriate file path! " + args[2]);
            System.exit(0);
        }

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

        OperationType operationType;

        switch (type.toLowerCase()){
            case "search": {
                operationType =(Search) applicationContext.getBean("search");
                operationType.makeResponse(inFile, outFile);
                break;
            }
            case "stat": {
                operationType =(Stat) applicationContext.getBean("stat");
                operationType.makeResponse(inFile, outFile);
                break;
            }
            default: System.out.println("Wrong operation type! Available types: search, stat");
        }
    }
}
