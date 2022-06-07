package com.savchenko.controlsystem.config;

import java.io.IOException;
import java.util.Properties;

public class ProjectPropertiesLoader {
    public static ProjectPropertiesLoader instance = new ProjectPropertiesLoader();
    private Properties properties;

    private ProjectPropertiesLoader(){
        properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("projectstructure.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Can not find \"projectstructure.properties\" by path \"resources/\"");
        }
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
