package com.savchenko.dsl

import java.text.SimpleDateFormat
import static com.savchenko.dsl.closure.DSL.*

println "╔═══╦╗─╔═══╦══╦╗─╔╦═══╦╗╔╦══╦╗╔╗ ╔═══╦═══╦══╦══╗ \n" +
        "║╔═╗║║─║╔══╣╔═╣╚═╝║╔══╣║║╠╗╔╣║║║ ║╔══╣╔═╗╠╗╔╣╔╗║ \n" +
        "║╚═╝║║─║╚══╣╚═╣╔╗─║╚══╣║║║║║║╚╝║ ║║╔═╣╚═╝║║║║╚╝╚╗\n" +
        "║╔══╣║─║╔══╩═╗║║╚╗║╔══╣╚╝║║║╚═╗║ ║║╚╗║╔╗╔╝║║║╔═╗║\n" +
        "║║──║╚═╣╚══╦═╝║║─║║╚══╬╗╔╬╝╚╗╔╝║ ║╚═╝║║║║╔╝╚╣╚═╝║\n" +
        "╚╝──╚══╩═══╩══╩╝─╚╩═══╝╚╝╚══╝╚═╝ ╚═══╩╝╚╝╚══╩═══╝\n"


//date format! dd-mm-yyyy

static String addTwoWeeks(String date, int sdvig){
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    Calendar calendar = Calendar.getInstance()
    calendar.setTime(formatter.parse(date))
    calendar.add(Calendar.DAY_OF_MONTH, sdvig)
    return formatter.format(calendar.getTime())
}

class TMP {
    static String date = '8-6-2022'
}

environment {
    downloadDirectory '/home/egor/Downloads/tmp'
    htmlResponseDirectory '/home/egor/Downloads/res'
}

configuration {
    groups {
        group('20215'){
            students {
                student('PlesneviyGRIB','https://github.com/PlesneviyGRIB/OOP','Savchenko','Egor','Vladimirovich','main')
                student('EresK','https://github.com/EresK/OOP','Kuular','Eres','Albertovich','main')
            }
        }

        group('20214'){
            students {
                student('AnarCom','https://github.com/AnarCom/OOP','Dolgii','Alexander','Dmitrievich','main')
                student('luchshiyDed','https://github.com/luchshiyDed/OOP','Ivanov','Oleg','Irdemovich')
            }
        }
    }

    controlPoints {
        controlPoint('Control P1', '12-6-2022')
        //controlPoint('Control P2', '11-6-2022')
    }

    tasks {
        task('Task_1_1_1', 30, DSLanguage.addTwoWeeks(TMP.date,0),'task for threads')
        task('Task_1_1_2', 20, DSLanguage.addTwoWeeks(TMP.date,7),'task for executor services')
        task('Task_1_2_1', 40, '8-6-2022','task multiple resources')
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
}

attributes {
    addToMap('DEADLINE_20215PlesneviyGRIBTask_1_1_1','11-6-2022')

    group('20215'){
        student('PlesneviyGRIB') {
            passedTasks {
                passed('Task_1_1_1', '10-6-2022', 28,'almost done')
                passed('Task_1_1_2', '10-6-2022', 8,'nice job')
                passed('Task_1_2_1', '10-6-2022', 35,'bad')
                passed('Task_1_2_2', '10-6-2022', 128,'')
            }
            extraPoints {
                points('Task_1_1_1',7,'extended functionality')
            }
            attendance {
                absent('10-6-2022')
                attended('2-6-2022')
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