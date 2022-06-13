package com.savchenko.dsl.supportive

class HtmlFile {
    String path
    StringBuilder stringBuilder = new StringBuilder(
            "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"></head><body><p>\n" +
            "╔═══╦╗─╔═══╦══╦╗─╔╦═══╦╗╔╦══╦╗╔╗ ╔═══╦═══╦══╦══╗<br>\n" +
            "║╔═╗║║─║╔══╣╔═╣╚═╝║╔══╣║║╠╗╔╣║║║ ║╔══╣╔═╗╠╗╔╣╔╗║<br>\n" +
            "║╚═╝║║─║╚══╣╚═╣╔╗─║╚══╣║║║║║║╚╝║ ║║╔═╣╚═╝║║║║╚╝╚╗<br>\n" +
            "║╔══╣║─║╔══╩═╗║║╚╗║╔══╣╚╝║║║╚═╗║ ║║╚╗║╔╗╔╝║║║╔═╗║<br>\n" +
            "║║──║╚═╣╚══╦═╝║║─║║╚══╬╗╔╬╝╚╗╔╝║ ║╚═╝║║║║╔╝╚╣╚═╝║<br>\n" +
            "╚╝──╚══╩═══╩══╩╝─╚╩═══╝╚╝╚══╝╚═╝ ╚═══╩╝╚╝╚══╩═══╝</p>")

    HtmlFile(String path){
        this.path = path
    }

    void append(String str){
        stringBuilder.append(str).append('<br>')
    }

    void make(){
        stringBuilder.append(" </body></html>")
        File file = new File(path)
        file.createNewFile()
        try(FileWriter fileWriter = new FileWriter(file)){
            fileWriter.write(stringBuilder.toString())
        }
    }
}
