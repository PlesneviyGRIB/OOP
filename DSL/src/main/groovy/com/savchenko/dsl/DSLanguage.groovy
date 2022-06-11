package com.savchenko.dsl

import static com.savchenko.dsl.closure.DSL.*

println "╔═══╦╗─╔═══╦══╦╗─╔╦═══╦╗╔╦══╦╗╔╗ ╔═══╦═══╦══╦══╗ \n" +
        "║╔═╗║║─║╔══╣╔═╣╚═╝║╔══╣║║╠╗╔╣║║║ ║╔══╣╔═╗╠╗╔╣╔╗║ \n" +
        "║╚═╝║║─║╚══╣╚═╣╔╗─║╚══╣║║║║║║╚╝║ ║║╔═╣╚═╝║║║║╚╝╚╗\n" +
        "║╔══╣║─║╔══╩═╗║║╚╗║╔══╣╚╝║║║╚═╗║ ║║╚╗║╔╗╔╝║║║╔═╗║\n" +
        "║║──║╚═╣╚══╦═╝║║─║║╚══╬╗╔╬╝╚╗╔╝║ ║╚═╝║║║║╔╝╚╣╚═╝║\n" +
        "╚╝──╚══╩═══╩══╩╝─╚╩═══╝╚╝╚══╝╚═╝ ╚═══╩╝╚╝╚══╩═══╝\n"

environment {
    downloadDirectory'/home/egor/Downloads/tmp'
    htmlResponseDirectory '/home/egor/Downloads/res'
}

configuration {
    groups {
        group('20215'){
            students {
                student(new Student('PlesneviyGRIB','https://github.com/PlesneviyGRIB/OOP','ad','asd','asd','main'))
            }
        }
        group('20214'){
            students {
                student(new Student('PlesneviyGRIB','https://github.com/PlesneviyGRIB/OOP','ad','asd','asd','main'))
            }
        }
    }

    tasks {
        task(new Task('Task_1_1_1', 30,'10-12-2022', 'first task'))
    }

    lessons {
        lesson(new Lesson('4-6-2022'))
    }
}

buildconfig {
    group('20215'){
        //gitLoader()
        //assemble()
        //javaDoc()
        attendance()
    }
    group('20214'){
        //gitLoader()
        //assemble()
        //javaDoc()
        attendance()
    }
}

attributes {
    group('20215'){
        student('PlesneviyGRIB'){
            attendance()
            passedTasks()
            extraPoints()
        }

        student('EresK'){
            attendance()
            passedTasks()
            extraPoints()
        }
    }
}
