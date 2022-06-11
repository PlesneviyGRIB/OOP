package com.savchenko.dsl.closure

import static groovy.lang.Closure.DELEGATE_ONLY

class Attributes {
    class Group{
        class AttributeParams{
            void extraPoints(){}
            void passedTasks(){}
            void attendance(){}
        }

        void student(String id, @DelegatesTo(value = AttributeParams, strategy = DELEGATE_ONLY) Closure closure){
            closure.delegate = new AttributeParams()
            closure.resolveStrategy = DELEGATE_ONLY
            closure.call()
        }
    }

    void group(String group, @DelegatesTo(value = Group, strategy = DELEGATE_ONLY) Closure closure){
        closure.delegate = new Group()
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }
}