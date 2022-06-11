package com.savchenko.dsl

import lombok.Getter
import lombok.Setter
import lombok.ToString

@Setter
@Getter
@ToString
class GroupConfiguration {
    Group group
    List<Task> tasks
    List<IncomingTask> incomingTasks
    List<Lesson> lessons
    List<ControlPoint> controlPoints
}
