package com.savchenko.controlsystem.readers;

import com.savchenko.controlsystem.config.ProjectPropertiesLoader;
import com.savchenko.controlsystem.models.Task;
import lombok.AllArgsConstructor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class TasksReader {
    private String group;

    public List<Task> read(){
        String path = ProjectPropertiesLoader.instance.getProperty("groupsLocation") + group + "/tasks";
        List<Task> tasks = new ArrayList<>();

        try(Scanner scanner = new Scanner(new File(path))) {
            while(scanner.hasNext()) {
                String tmp = scanner.nextLine();
                if (!CommentsChecker.check(tmp)) tasks.add(parseTask(tmp));
            }
        } catch (FileNotFoundException e) { throw new RuntimeException("Can not find file \"tasks\" by path \""+ path +"\""); }

        return tasks;
    }

    private Task parseTask(String str){
        String[] data = str.replaceAll("[\\s]{2,}", " ").trim().split(" ");

        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 2; i < data.length; i++) stringBuilder.append(data[i]).append(" ");

        try{
            return new Task(data[0], Integer.parseInt(data[1]), stringBuilder.toString().trim());
        }catch (Exception e) { throw new RuntimeException("Wrong task representation: ID POINTS(int) DESCRIPTION(optional)"); }
    }
}
