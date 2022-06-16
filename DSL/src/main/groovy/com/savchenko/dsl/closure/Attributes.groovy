package com.savchenko.dsl.closure

import lombok.Getter
import lombok.ToString
import java.text.SimpleDateFormat
import static groovy.lang.Closure.DELEGATE_ONLY

@ToString
@Getter
class Attributes {
    @ToString
    class Group{
        @Getter
        class AttributeParams{
            class Passed{
                private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                void passed(String taskId, String date, int points, String text){
                    map.put("${name}${studentId}passed${taskId}","${points} ${date} ${text}")
                    println("${name} ${studentId} PASSED ${taskId} [${formatter.parse(date).toString()}]: \"${points}\" (${text})")
                }
            }

            class Attendance{
                private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                void attended (String date){
                    map.put("${name}${studentId}attendance${formatter.parse(date).toString()}","true")
                    println("${name} ${studentId} ATTENDANCE --force [${formatter.parse(date).toString()}] true")
                }

                void absent (String date){
                    map.put("${name}${studentId}attendance${formatter.parse(date).toString()}","false")
                    println("${name} ${studentId} ATTENDANCE --force [${formatter.parse(date).toString()}] false")
                }
            }

            class Points{
                void points(String task, int points, String description){
                    map.put("${name}${studentId}points${task}","${points} ${description}")
                    println("${name} ${studentId} EXTAPOINTS for ${task}: \"${points}\" (${description})")
                }
            }

            String studentId

            AttributeParams(String studentId){
                this.studentId = studentId
            }

            void extraPoints(@DelegatesTo(value = Points, strategy = DELEGATE_ONLY) Closure closure){
                closure.delegate = new Points()
                closure.resolveStrategy = DELEGATE_ONLY
                closure.call()
            }
            void passedTasks(@DelegatesTo(value = Passed, strategy = DELEGATE_ONLY) Closure closure){
                closure.delegate = new Passed()
                closure.resolveStrategy = DELEGATE_ONLY
                closure.call()
            }
            void attendance(@DelegatesTo(value = Attendance, strategy = DELEGATE_ONLY) Closure closure){
                closure.delegate = new Attendance()
                closure.resolveStrategy = DELEGATE_ONLY
                closure.call()}
        }

        @Getter
        String name;

        Group(String name){
            this.name = name
        }

        void student(String id, @DelegatesTo(value = AttributeParams, strategy = DELEGATE_ONLY) Closure closure){
            closure.delegate = new AttributeParams(id)
            closure.resolveStrategy = DELEGATE_ONLY
            closure.call()
        }
    }

    Map<String,String> map;

    Attributes(Map<String, String> map){
        this.map = map
    }

    void addToMap(String key, String value){ map.put(key, value) }

    static List<Group> groups = new ArrayList<>()

    void group(String group, @DelegatesTo(value = Group, strategy = DELEGATE_ONLY) Closure closure){
        Group gr = new Group(group)
        closure.delegate = gr
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
        groups.add(gr)
    }
}