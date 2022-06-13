package com.savchenko.dsl.supportive

import com.savchenko.dsl.ControlPoint
import com.savchenko.dsl.Task

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
        int max = 0, cur = 0, add = 0

        tasks.forEach(t-> {
            if(t <= controlPoint) {
                max+= t.getPoints()
                String res = map.get("${group}${student}passed${t.getId()}")
                if(res != null) {
                    cur += Integer.parseInt( res.split(' ')[0])
                }

                String pointStr = map.get("${group}${student}points${t.getId()}")
                if(pointStr != null) add += Integer.parseInt(map.get("${group}${student}points${t.getId()}").split(' ')[0])
            }
        })
        return "${cur}+(${add}) from ${max}"
    }
}
