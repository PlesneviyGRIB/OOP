package com.savchenko.dsl.config

import com.savchenko.dsl.Group

import static groovy.lang.Closure.DELEGATE_ONLY

class GroupsLvlConfiguration{

    List<GroupConfiguration> groupsConfigurations = new ArrayList<>()
    ModelConfiguration modelConfiguration = new ModelConfiguration()

    class GroupParam{
        void group(String group, @DelegatesTo(value = ModelConfiguration, strategy = DELEGATE_ONLY) Closure closure){
            def modelConfiguration = new ModelConfiguration()
            closure.delegate = modelConfiguration
            closure.resolveStrategy = DELEGATE_ONLY
            closure.call()
            groupsConfigurations.add(matchMCtoGC(new Group(group), modelConfiguration))
        }
    }

    void groups(@DelegatesTo(value = GroupParam, strategy = DELEGATE_ONLY) Closure closure){
        closure.delegate = new GroupParam()
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    void tasks (@DelegatesTo(value = ModelConfiguration.TaskParam, strategy = DELEGATE_ONLY) Closure closure) {
        closure.delegate = modelConfiguration.taskParam
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    void lessons(@DelegatesTo(value = ModelConfiguration.LessonParam, strategy = DELEGATE_ONLY) Closure closure){
        closure.delegate = modelConfiguration.lessonParam
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    void controlPoints(@DelegatesTo(value = ModelConfiguration.ControlPointParam, strategy = DELEGATE_ONLY) Closure closure){
        closure.delegate = modelConfiguration.controlPointParam
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    private GroupConfiguration matchMCtoGC(Group group, ModelConfiguration mc){
        def gc = new GroupConfiguration()
        mc.getStudentsList().forEach(s -> group.addStudent(s))
        gc.setGroup(group)
        gc.setControlPoints(mc.controlPointsList)
        gc.setLessons(mc.lessonsList)
        gc.setTasks(mc.tasksList)
        return gc
    }

    List<GroupConfiguration> getGroupConfiguration(){
        Set set
        groupsConfigurations.forEach(gc ->{
            set = new HashSet(gc.getControlPoints()); set.addAll(modelConfiguration.getControlPointsList()); gc.setControlPoints(new ArrayList<>(set))
            set = new HashSet(gc.getLessons()); set.addAll(modelConfiguration.getLessonsList()); gc.setLessons(new ArrayList<>(set))
            set = new HashSet(gc.getTasks()); set.addAll(modelConfiguration.getTasksList()); gc.setTasks(new ArrayList<>(set))
        })
        return groupsConfigurations
    }
}