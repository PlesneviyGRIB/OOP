package com.savchenko.controlsystem.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ProjectPropertiesLoader {
    //private static final String PATH = "./src/main/resources/settings/projectstructure.properties";
    private static final String PATH = "/home/egor/IdeaProjects/AutomaticControlSystem/src/main/resources/settings/projectstructure.properties";

    public static ProjectPropertiesLoader instance = new ProjectPropertiesLoader();
    private Properties properties;

    private ProjectPropertiesLoader(){
        properties = new Properties();
        try {
            properties.load(new FileReader(PATH));
        } catch (IOException e) {
            throw new RuntimeException("Can not find \"projectstructure.properties\" by path \"" + PATH +"\"");
        }
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
