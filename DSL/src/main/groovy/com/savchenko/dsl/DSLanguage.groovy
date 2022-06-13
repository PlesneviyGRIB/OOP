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

    lessons {
        lesson('7-6-2022')
        lesson('11-6-2022')
        lesson('7-5-2022')
        lesson('11-5-2022')
        lesson('7-4-2022')
        lesson('30-3-2022')
    }

    tasks {
        task('Task_1_1_1', 30,'10-6-2022','done')
        task('Task_1_1_2', 20,'11-6-2022','done')
        task('Task_1_1_3', 30,'12-6-2022','done')
        task('Task_1_1_4', 80,'13-6-2022','done')
    }

    controlPoints {
        controlPoint('Control P1', '10-6-2022')
        controlPoint('Control P2', '11-6-2022')
    }
}


buildconfig {
    group('20215'){
        gitLoader()
        attendance()
    }
    group('20214'){
        gitLoader()
        attendance()
    }
}


attributes {
    group('20215'){
        student('PlesneviyGRIB') {
            passedTasks {
                passed('Task_1_1_1', '10-6-2022', 28,'done')
                passed('Task_1_1_2', '10-6-2022', 8,'done')
                passed('Task_1_1_3', '10-6-2022', 28,'done')
                passed('Task_1_1_4', '10-6-2022', 128,'done')
            }
            extraPoints {
                points('Task_1_1_4', 10,'nice')
                points('Task_1_1_2', 5,'nice')
            }
        }

        student('EresK') {
            passedTasks {
                passed('Task_1_1_1', '10-6-2022', 40,'done')
                passed('Task_1_1_2', '10-6-2022', 60,'done')
                passed('Task_1_1_3', '10-6-2022', 15,'done')
                passed('Task_1_1_4', '10-6-2022', 47,'done')
            }
            extraPoints {
                points('Task_1_1_1', 7,'nice')
                points('Task_1_1_2', -5,'nice')
            }
        }
    }

    group('20214'){
        student('AnarCom') {
            passedTasks {
                passed('Task_1_1_1', '10-6-2022', 58,'done')
                passed('Task_1_1_2', '10-6-2022', 8,'done')
                passed('Task_1_1_3', '10-6-2022', 28,'done')
                passed('Task_1_1_4', '10-6-2022', 18,'done')
            }
            extraPoints {
                points('Task_1_1_4', 10,'nice')
                points('Task_1_1_2', -5,'nice')
            }
        }

        student('luchshiyDed') {
            passedTasks {
                passed('Task_1_1_1', '10-6-2022', 50,'done')
                passed('Task_1_1_2', '10-6-2022', 30,'done')
            }
            extraPoints {

                points('Task_1_1_2', -25,'nice')
            }
        }
    }
}

makeHTMLresponse()