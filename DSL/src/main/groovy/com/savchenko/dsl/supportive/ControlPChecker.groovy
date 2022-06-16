package com.savchenko.dsl.supportive

import com.savchenko.dsl.ControlPoint
import com.savchenko.dsl.Task

import java.text.SimpleDateFormat

class ControlPChecker {
    String group
    String student
    ControlPoint controlPoint
    List<Task> tasks
    Map<String, String> map

    ControlPChecker(String group, String student, ControlPoint controlPoint, List<Task> tasks, Map<String, String> map) {
        this.group = group
        this.student = student
        this.controlPoint = controlPoint
        this.tasks = tasks
        this.map = map
    }

    String getResult(){
        int max = 0, cur = 0, add = 0, deadlinePoints = 0

        tasks.forEach(t-> {
            Task taskWithSpecialDeadline = checkSpecialDeadLine(t)
            if(taskWithSpecialDeadline != null) t = taskWithSpecialDeadline

            if(t <= controlPoint) {
                max+= t.getPoints()
                String res = map.get("${group}${student}passed${t.getId()}")
                if(res != null) {
                    cur += Integer.parseInt( res.split(' ')[0])
                    if(checkDeadline(t.getDeadLine(),res.split(' ')[1])) {
                        deadlinePoints += Integer.parseInt( res.split(' ')[0])
                    }
                }

                String pointStr = map.get("${group}${student}points${t.getId()}")
                if(pointStr != null) add += Integer.parseInt(map.get("${group}${student}points${t.getId()}").split(' ')[0])
            }
        })
        return "[${deadlinePoints} | ${cur}]+(${add}) from ${max}"
    }

    Task checkSpecialDeadLine(Task task){
        String exp = "DEADLINE_${group}${student}${task.getId()}"
        String newDeadLine = map.get(exp)
        return newDeadLine == null ? null: new Task(task.getId(), task.getPoints(), newDeadLine, task.getTitle())
    }

    boolean checkDeadline(Date deadline, String passDay){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.parse(passDay) <= deadline
    }
}
