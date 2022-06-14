package com.savchenko.dsl.closure

import com.savchenko.dsl.config.GroupsLvlConfiguration
import com.savchenko.dsl.supportive.HtmlFile
import com.savchenko.dsl.supportive.Response
import lombok.Getter
import static groovy.lang.Closure.DELEGATE_ONLY

@Getter
class DSL{
    static EnvironmentParams environmentParams = new EnvironmentParams()
    static GroupsLvlConfiguration groupsLvlConfiguration = new GroupsLvlConfiguration()
    static Map<String, String> allData = new HashMap<>()

    static void environment(@DelegatesTo(value = EnvironmentParams, strategy = DELEGATE_ONLY) Closure closure){
        closure.delegate = environmentParams
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    static void configuration(@DelegatesTo(value = GroupsLvlConfiguration, strategy = DELEGATE_ONLY) Closure closure){
        closure.delegate = groupsLvlConfiguration
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    static void buildconfig(@DelegatesTo(value = BuildGroup, strategy = DELEGATE_ONLY) Closure closure){
        closure.delegate = new BuildGroup(environmentParams, groupsLvlConfiguration.groupConfiguration, allData)
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    static void attributes(@DelegatesTo(value = Attributes, strategy = DELEGATE_ONLY) Closure closure){
        Attributes attributes = new Attributes(allData)
        closure.delegate = attributes
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    static void makeHTMLresponse(){
        println('४०॰०॰०॰०॰०॰०॰०॰०॰०॰०॰०॰०॰०॰०॰०॰००॰०॰०॰०॰०॰०॰०॰०॰०॰०॰०॰०॰०॰०॰०॰०॰०॰०॰०४')
        println("html file location: \"${environmentParams.getHtmlResponseDirectory()}\"")

        HtmlFile htmlFile = new HtmlFile(environmentParams.getHtmlResponseDirectory() + '/response.html')
        groupsLvlConfiguration.getGroupConfiguration().forEach(glc-> {
            Response response = new Response(allData, glc)
            htmlFile.append(response.header())
            htmlFile.append(response.academicPerformance())
            htmlFile.append(response.attendance())
            htmlFile.append(response.tasksDetails())
        })
        htmlFile.make()
    }
}

@Getter
class EnvironmentParams{
    String downloadDirectory = '/home/egor/tmp'
    String htmlResponseDirectory = '/home/egor/tmp/response'

    void downloadDirectory (String path){
        downloadDirectory = path
    }

    void htmlResponseDirectory (String path){
        htmlResponseDirectory = path
    }
}