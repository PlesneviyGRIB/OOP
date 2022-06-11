package com.savchenko.dsl.closure


import com.savchenko.dsl.config.GroupsLvlConfiguration
import lombok.Getter
import static groovy.lang.Closure.DELEGATE_ONLY

class DSL{
    static EnvironmentParams environmentParams = new EnvironmentParams()
    static GroupsLvlConfiguration groupsLvlConfiguration = new GroupsLvlConfiguration()


    static void environment(@DelegatesTo(value = EnvironmentParams, strategy = DELEGATE_ONLY) Closure closure){
        closure.delegate = environmentParams
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    static void configuration(@DelegatesTo(value = GroupsLvlConfiguration, strategy = DELEGATE_ONLY) Closure closure){
        closure.delegate = groupsLvlConfiguration
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
        groupsLvlConfiguration.getGroupConfiguration()
    }

    static void buildconfig(@DelegatesTo(value = BuildGroup, strategy = DELEGATE_ONLY) Closure closure){
        closure.delegate = new BuildGroup(environmentParams, groupsLvlConfiguration.groupConfiguration)
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    static void attributes(@DelegatesTo(value = Attributes, strategy = DELEGATE_ONLY) Closure closure){
        closure.delegate = new Attributes()
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }
}

@Getter
class EnvironmentParams{
    String downloadDirectory
    String htmlResponseDirectory


    void downloadDirectory (String path){
        downloadDirectory = path
    }

    void htmlResponseDirectory (String path){
        htmlResponseDirectory = path
    }
}