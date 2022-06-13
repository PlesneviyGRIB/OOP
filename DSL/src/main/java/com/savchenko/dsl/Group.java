package com.savchenko.dsl;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Group {
    private final String groupName;
    private final List<Student>  students = new ArrayList<>();

    public void deleteStudent(Student student){
        students.remove(student);
    }

    public void addStudent(Student student){
        students.add(student);
    }
}
