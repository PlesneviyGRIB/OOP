package com.savchenko.controlsystem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
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
