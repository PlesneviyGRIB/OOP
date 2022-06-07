package com.savchenko.controlsystem

import com.savchenko.controlsystem.config.ProjectPropertiesLoader
import com.savchenko.controlsystem.readers.GroupStudentsReader
import com.savchenko.controlsystem.readers.TasksReader

groupTitle = ProjectPropertiesLoader.instance.getProperty('group')

group = new GroupStudentsReader(groupTitle).read()
tasks = new TasksReader(groupTitle).read()

group.students.forEach(student ->
{
    file = new File(ProjectPropertiesLoader.instance.getProperty('path') + student.nickName)
    file.mkdir()
    tasks.forEach(task -> {
        path = file.path + '/' + task.getId()
        url = student.getUrl().toString() + "/trunk/" + task.getId()
        ProcessBuilder processBuilder = new ProcessBuilder("svn", "export", url, path)
        processBuilder.start()
    })
})