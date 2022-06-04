package com.savchenko.controlsystem.gitapi;

import com.savchenko.controlsystem.config.ProjectPropertiesLoader;
import com.savchenko.controlsystem.models.Student;
import com.savchenko.controlsystem.models.Task;
import java.io.*;

public class GitLoader {
    private Student student;
    private Task task;
    public GitLoader(Student student, Task task){
        this.student = student;
        this.task = task;
        load();
    }

    private boolean load(){
        try {
            Process process = Runtime.getRuntime().exec("svn export " + student.getUrl() + "/trunk/" + task.getId() + " " + mkDir(task.getId()));
            process.waitFor();
        } catch (IOException | InterruptedException e) { throw new RuntimeException(e); }
        return true;
    }

    private String mkDir(String path) {
        File file = new File(ProjectPropertiesLoader.instance.getProperty("path"));
        if(!file.isDirectory()) throw new RuntimeException("Unable to download files by path \"" + file.getAbsolutePath() +"\" - it is not directory");

        file = new File( file.getAbsolutePath() + "/" + student.getNickName());
        if(!file.exists()) file.mkdir();

        return file.getAbsolutePath() + "/" + path;
    }
}
