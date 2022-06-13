package com.savchenko.dsl.config

import com.savchenko.dsl.ControlPoint
import com.savchenko.dsl.Lesson
import com.savchenko.dsl.Student
import com.savchenko.dsl.Task
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

    public final TaskParam taskParam = new TaskParam()

    class TaskParam {
        void task(String id, int points, String deadLine, String title){
            tasksList.add(new Task(id, points, deadLine, title))
        }
    }

    public final StudentParam studentParam = new StudentParam()

    class StudentParam{
        void student(String nickName, String url, String surname, String name, String patronymic, String branchName){
            studentsList.add(new Student(nickName, url, surname, name, patronymic, branchName))
        }
        void student(String nickName, String url, String surname, String name, String patronymic){
            studentsList.add(new Student(nickName, url, surname, name, patronymic))
        }
    }

    public final LessonParam lessonParam = new LessonParam()

    class LessonParam{
        void lesson(String date){
            lessonsList.add(new Lesson(date))
        }
    }

    public final ControlPointParam controlPointParam = new ControlPointParam()

    class ControlPointParam{
        void controlPoint(String controlPointName, String date){
            controlPointsList.add(new ControlPoint(controlPointName, date))
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
}