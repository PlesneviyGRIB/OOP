package com.savchenko.dsl.config

import com.savchenko.dsl.ControlPoint
import com.savchenko.dsl.Group

import com.savchenko.dsl.Lesson
import com.savchenko.dsl.Task
import lombok.Getter
import lombok.Setter
import lombok.ToString

@Setter
@Getter
@ToString
class GroupConfiguration {
    Group group
    List<Task> tasks
    List<Lesson> lessons
    List<ControlPoint> controlPoints
}
