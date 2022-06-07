package com.savchenko.controlsystem

import com.savchenko.controlsystem.config.ProjectPropertiesLoader
import com.savchenko.controlsystem.models.Student
import com.savchenko.controlsystem.models.Task

class TaskChecker {
    Student student
    Task task

    TaskChecker(Student student, Task task){
        this.task = task
        this.student = student
    }

    boolean assemble(){
        return processed(new ProcessBuilder("gradle", "assemble"))
    }

    boolean test(){
        return processed(new ProcessBuilder("gradle", "test"))
    }

    boolean javadoc(){
        return processed(new ProcessBuilder("gradle", "javadoc"))
    }

    void removeBuild(){
        processed(new ProcessBuilder("gradle", "clean"))
    }

    private boolean processed(ProcessBuilder processBuilder){
        processBuilder.directory(new File(ProjectPropertiesLoader.instance.getProperty('path') + student.getNickName() + '/' + task.id))
        Process process = processBuilder.start()
        process.waitFor()
        return parseOut(process.text)
    }

    private boolean parseOut(String out){
        return out.contains("BUILD SUCCESSFUL")
    }
}