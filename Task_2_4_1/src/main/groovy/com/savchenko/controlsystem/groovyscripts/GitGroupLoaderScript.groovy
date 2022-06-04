package com.savchenko.controlsystem.groovyscripts

import com.savchenko.controlsystem.gitapi.GitLoader
import com.savchenko.controlsystem.readers.StudentReader
import com.savchenko.controlsystem.readers.TasksReader

group = '20215'

students = new StudentReader(group).read()
tasks = new TasksReader(group).read()

students.forEach(student -> tasks.forEach(task -> new GitLoader(student, task)))