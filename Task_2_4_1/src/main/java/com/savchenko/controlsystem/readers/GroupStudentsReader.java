package com.savchenko.controlsystem.readers;

import com.savchenko.controlsystem.config.ProjectPropertiesLoader;
import com.savchenko.controlsystem.models.Group;
import com.savchenko.controlsystem.models.Student;
import lombok.AllArgsConstructor;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class GroupStudentsReader {
    private String group;

    public Group read(){
        String path = ProjectPropertiesLoader.instance.getProperty("groupsLocation") + group + "/students";
        List<Student> students = new ArrayList<>();

        try(Scanner scanner = new Scanner(new File(path))){
            while (scanner.hasNextLine()) {
                String tmp = scanner.nextLine();
                if (!CommentsChecker.check(tmp)) students.add(parseStudent(tmp));
            }
        } catch (FileNotFoundException e) { throw new RuntimeException("Can not find file \"students\" by path \""+ path +"\""); }
          catch (MalformedURLException e) { throw new RuntimeException("Wrong URL from file: \"" + path + "\""); }

        Group gr = new Group(group);
        students.forEach(gr::addStudent);
        return gr;
    }

    private Student parseStudent(String str) throws MalformedURLException {
        String[] data = str.replaceAll("[\\s]{2,}", " ").trim().split(" ");
        try{
            if(data.length == 6) return  new Student(data[0], new URL(data[1]), data[2], data[3], data[4], data[5]);
            return new Student(data[0], new URL(data[1]), data[2], data[3], data[4]);
        }catch (IndexOutOfBoundsException e) { throw new RuntimeException("Wrong student representation: NICK URL NAME SURNAME PATRONYMIC BRANCH(optional)"); }
    }
}