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
    downloadDirectory '/home/egor/Downloads/tmp'
    htmlResponseDirectory '/home/egor/Downloads/res'
}

configuration {
    groups {
        group('20215'){
            students {
                student('PlesneviyGRIB','https://github.com/PlesneviyGRIB/OOP','Savchenko','Egor','Vladimirovich','main')
                student('EresK','https://github.com/EresK/OOP','Kuular','Eres','Vladimirovich','main')
            }
        }

        group('20214'){
            students {
                student('AnarCom','https://github.com/AnarCom/OOP','Dolgii','Alexander','Dmitrievich','main')
                student('luchshiyDed','https://github.com/luchshiyDed/OOP','Ivanov','Oleg','Irdemovich','main')
            }
        }
    }

    controlPoints {
        controlPoint('Control P1', '10-6-2022')
        controlPoint('Control P2', '11-6-2022')
    }

    tasks {
        task('Task_1_1_1', 30, '8-6-2022','task for threads')
        task('Task_1_1_2', 20, '9-6-2022','task for executor services')
        task('Task_1_2_1', 40, '10-6-2022','task multiple resources')
    }

    lessons {
        lesson('7-6-2022')
        lesson('11-6-2022')
        lesson('7-5-2022')
        lesson('11-5-2022')
        lesson('7-4-2022')
        lesson('30-3-2022')
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

    group('20214'){
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
            passedTasks {
                passed('Task_1_1_1', '10-6-2022', 28,'almost done')
                passed('Task_1_1_2', '10-6-2022', 8,'nice job')
                passed('Task_1_2_1', '10-6-2022', 28,'bad')
                passed('Task_1_2_2', '10-6-2022', 128,'')
            }
        }
        student('EresK') {
            passedTasks {
                passed('Task_1_1_1', '10-6-2022', 28,'')
                passed('Task_1_1_2', '10-6-2022', 8,'')
                passed('Task_1_2_1', '10-6-2022', 28,'')
                passed('Task_1_2_2', '10-6-2022', 128,'')
            }
        }
    }
}

makeHTMLresponse()