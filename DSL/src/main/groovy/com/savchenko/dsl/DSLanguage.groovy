package com.savchenko.dsl

import static com.savchenko.dsl.DSL.*

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
                student(new Student('EresK','https://github.com/EresK/OOP','ad','asd','asd','main'))
            }

            tasks {
                task(new Task('Task_1_1_1',30,'first'))
            }

            incomingTasks {
                incomingTask(new IncomingTask('Task_1_1_1', '10-10-2022'))
            }
        }
    }
}

buildconfig {
    group('20215'){
        //gitLoader()
        assemble()
    }
}



