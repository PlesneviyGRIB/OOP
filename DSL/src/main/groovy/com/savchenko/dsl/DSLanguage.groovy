package com.savchenko.dsl

import static com.savchenko.dsl.closure.DSL.*

println "╔═══╦╗─╔═══╦══╦╗─╔╦═══╦╗╔╦══╦╗╔╗ ╔═══╦═══╦══╦══╗ \n" +
        "║╔═╗║║─║╔══╣╔═╣╚═╝║╔══╣║║╠╗╔╣║║║ ║╔══╣╔═╗╠╗╔╣╔╗║ \n" +
        "║╚═╝║║─║╚══╣╚═╣╔╗─║╚══╣║║║║║║╚╝║ ║║╔═╣╚═╝║║║║╚╝╚╗\n" +
        "║╔══╣║─║╔══╩═╗║║╚╗║╔══╣╚╝║║║╚═╗║ ║║╚╗║╔╗╔╝║║║╔═╗║\n" +
        "║║──║╚═╣╚══╦═╝║║─║║╚══╬╗╔╬╝╚╗╔╝║ ║╚═╝║║║║╔╝╚╣╚═╝║\n" +
        "╚╝──╚══╩═══╩══╩╝─╚╩═══╝╚╝╚══╝╚═╝ ╚═══╩╝╚╝╚══╩═══╝\n"


//date format! dd-mm-yyyy


environment {
    downloadDirectory'/home/egor/Downloads/tmp'
    htmlResponseDirectory '/home/egor/Downloads/res'
}


configuration {
    groups {
        group('20215'){
            students {
                student('PlesneviyGRIB','https://github.com/PlesneviyGRIB/OOP','ad','asd','asd','main')
            }
            lessons {
                lesson('7-6-2022')
                lesson('11-6-2022')
            }
        }
    }
}


buildconfig {
    group('20215'){
        gitLoader()
        assemble()
        test()
        javaDoc()
        attendance()
    }
}


attributes {
    group('20215'){
        student('PlesneviyGRIB') {
            attendance {
                attended('8-06-2022')
                absent('7-06-2022')
            }
            passedTasks {
                passed('Task_1_1_1', '12-12-2022', 28, 'one test can not be passed')
            }
            extraPoints {
                points('Task_1_1_1', 5, 'several tests were added')
            }

        }
    }
}


makeHTMLresponse()