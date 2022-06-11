package com.savchenko.dsl.supportive

import java.text.SimpleDateFormat

class DateMatcher {
    SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
    String text
    Date date

    DateMatcher(String text, Date date){
        this.text = text
        this.date = date
    }

    boolean match(){
        boolean state
        text.split("\n").toList().forEach(d ->{
            Date date1 = formatter.parse(d.replace('\"',''))
            if(date1 == date) state = true
        })
        return state
    }
}
