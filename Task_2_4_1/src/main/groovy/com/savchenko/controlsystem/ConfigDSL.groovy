package com.savchenko.controlsystem

import com.savchenko.controlsystem.config.ProjectPropertiesLoader
import com.savchenko.controlsystem.models.Student
import com.savchenko.controlsystem.models.Task
import com.savchenko.controlsystem.readers.GroupStudentsReader
import com.savchenko.controlsystem.readers.TasksReader

class ConfigDSL {
    static String group = ProjectPropertiesLoader.instance.getProperty("group")

    static void main(String[] args) {

        //new GroovyShell(GitGroupLoaderScript)

        List<Student> students = new GroupStudentsReader(group).read().students
        List<Task> tasks = new TasksReader(group).read()


        students.forEach(student ->{
            println(student.nickName)
            tasks.forEach(task ->{
                if(taskExistence(student, task)) {
                    println("    " + task.id)
                    TaskChecker taskChecker = new TaskChecker(student, task)
                    println("        " + "assemble: " + taskChecker.assemble())
                    println("        " + "    test: " + taskChecker.test())
                    println("        " + " javadoc: " + taskChecker.javadoc())
                }
        })})
    }

    private static boolean taskExistence(Student student, Task task){
        try { return new File(ProjectPropertiesLoader.instance.getProperty("path") + student.nickName).list().contains(task.id) }
        catch(ignored) { return false }
    }
}
