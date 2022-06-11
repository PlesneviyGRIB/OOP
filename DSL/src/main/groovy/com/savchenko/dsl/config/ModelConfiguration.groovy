package com.savchenko.dsl

import lombok.Getter
import lombok.ToString

import static groovy.lang.Closure.DELEGATE_ONLY

@Getter
@ToString
class ModelConfiguration {

    List<Task> tasksList = new ArrayList()
    List<Student> studentsList = new ArrayList<>()
    List<ControlPoint> controlPointsList = new ArrayList()
    List<Lesson> lessonsList = new ArrayList()
    List<IncomingTask> incomingTasksList = new ArrayList()

    public final TaskParam taskParam = new TaskParam()

    class TaskParam {
        void task(Task task){
            tasksList.add(task)
        }
    }

    public final StudentParam studentParam = new StudentParam()

    class StudentParam{
        void student(Student student){
            studentsList.add(student)
        }
    }

    public final LessonParam lessonParam = new LessonParam()

    class LessonParam{
        void lesson(Lesson lesson){
            lessonsList.add(lesson)
        }
    }

    public final ControlPointParam controlPointParam = new ControlPointParam()

    class ControlPointParam{
        void controlPoint(ControlPoint controlPoint){
            controlPointsList.add(controlPoint)
        }
    }

    public final IncomingTaskParam  incomingTaskParam = new IncomingTaskParam()

    class IncomingTaskParam{
        void incomingTask(IncomingTask incomingTask){
            incomingTasksList.add(incomingTask)
        }
    }

    void tasks (@DelegatesTo(value = TaskParam, strategy = DELEGATE_ONLY) Closure closure) {
        closure.delegate = taskParam
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    void students(@DelegatesTo(value = StudentParam, strategy = DELEGATE_ONLY) Closure closure){
        closure.delegate = studentParam
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    void lessons(@DelegatesTo(value = LessonParam, strategy = DELEGATE_ONLY) Closure closure){
        closure.delegate = lessonParam
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    void controlPoints(@DelegatesTo(value = ControlPointParam, strategy = DELEGATE_ONLY) Closure closure){
        closure.delegate = controlPointParam
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    void incomingTasks(@DelegatesTo(value = IncomingTaskParam, strategy = DELEGATE_ONLY) Closure closure){
        closure.delegate = incomingTaskParam
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }
}