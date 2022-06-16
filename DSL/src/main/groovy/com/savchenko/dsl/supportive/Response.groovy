package com.savchenko.dsl.supportive

import com.savchenko.dsl.config.GroupConfiguration
import java.text.SimpleDateFormat

class Response {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    Map<String, String> map
    GroupConfiguration gConfig
    StringBuilder attTable = new StringBuilder("<h3>ATTENDANCE</h3><table border=\"1\" cellspacing=\"0\">")
    StringBuilder apTable = new StringBuilder("<h3>ACADEMIC PERFORMANCE</h3><table border=\"1\" cellspacing=\"0\">")
    StringBuilder tdTable = new StringBuilder("<h3>TASKS DETAILS</h3><table border=\"1\" cellspacing=\"0\">")

    Response(Map<String, String> map, GroupConfiguration groupConfiguration){
        this.map = map
        this.gConfig = groupConfiguration
    }

    String header(){
        return "<br><h2>${gConfig.group.getGroupName()}<h2>"
    }

    String tasksDetails(){
        StringBuilder tmp = new StringBuilder()
        appendTag(tmp,"th", "")
        gConfig.tasks.sort().forEach(t -> {
            appendTag(tmp,"th", t.getId())
            appendTag(tmp, 'td',"(${t.getTitle()})")
        })
        appendTag(tdTable,"tr", tmp.toString())

        gConfig.getGroup().getStudents().sort().forEach(s->{
            tmp.setLength(0)
            appendTag(tmp,"th align=\"left\"", "${s.getSurname()} ${s.getName()} ${s.getPatronymic()}")
            gConfig.getTasks().sort().forEach(t -> {

                String res = map.get("${gConfig.group.getGroupName()}${s.getNickName()}passed${t.getId()}")
                if(res != null) {
                    appendTag(tmp, "td align=\"center\"", internalTable(s.getNickName(),t.getId()))
                    appendTag(tmp, "td align=\"center\"", "\"${res.split(' ', 3)[2]}\"")
                } else {
                    appendTag(tmp, "td align=\"center\"", 'did not pass')
                    appendTag(tmp, "td align=\"center\"", '-')
                }
            })
            appendTag(tdTable,"tr", tmp.toString())
        })

        tdTable.append("</table>")

        return tdTable.toString()
    }

    private String internalTable(String nick, String taskId){
        StringBuilder temp = new StringBuilder("<table border=\"1\" cellspacing=\"0\" width=\"100%\">" )

        StringBuilder tmp = new StringBuilder()
        appendTag(tmp,"th align=\"center\"", "assemble ⚙")
        appendTag(tmp,"th align=\"center\"", "test 🛠")
        appendTag(tmp,"th align=\"center\"", "javaDoc 📖")
        appendTag(temp,"tr", tmp.toString())
        tmp.setLength(0)

        String res = map.get("${gConfig.group.getGroupName()}${nick}assemble${taskId}")
        if(res != null) appendTag(tmp, "td align=\"center\"", "${res}")
        else appendTag(tmp, "td align=\"center\"", "-")
        res = map.get("${gConfig.group.getGroupName()}${nick}test${taskId}")
        if(res != null) appendTag(tmp, "td align=\"center\"", "${res}")
        else appendTag(tmp, "td align=\"center\"", "-")
        res = map.get("${gConfig.group.getGroupName()}${nick}javaDoc${taskId}")
        if(res != null) appendTag(tmp, "td align=\"center\"", "${res}")
        else appendTag(tmp, "td align=\"center\"", "-")

        appendTag(temp,"tr", tmp.toString())
        temp.append("</table>")

        return temp.toString()
    }

    String academicPerformance(){
        StringBuilder tmp = new StringBuilder()
        appendTag(tmp,"th", "")
        gConfig.getTasks().sort().forEach(t -> {appendTag(tmp,"th", t.getId())})
        gConfig.getControlPoints().sort().forEach(c -> {appendTag(tmp,"th", "${c.getControlPointName()} [${formatter.format(c.getDate()).toString()}]")})
        appendTag(tmp,"th", "TOTAL")
        appendTag(tmp,"th", "ADDITIONAL")
        appendTag(apTable,"tr", tmp.toString())


        gConfig.getGroup().getStudents().sort().forEach(s->{
            tmp.setLength(0)
            appendTag(tmp,"th align=\"left\"", "${s.getSurname()} ${s.getName()} ${s.getPatronymic()}")
            int sumC = 0, sumT = 0, sumP = 0
            gConfig.getTasks().sort().forEach(t -> {
                String res = map.get("${gConfig.group.getGroupName()}${s.getNickName()}passed${t.getId()}")
                sumT += t.getPoints()
                if(res != null) {
                    sumC += Integer.parseInt(res.split(' ')[0])
                    String pointStr = map.get("${gConfig.group.getGroupName()}${s.getNickName()}points${t.getId()}")
                    if(pointStr == null) appendTag(tmp, "td align=\"center\"", "${res.split(' ')[0]}/${t.getPoints()}")
                    else {
                        sumP += Integer.parseInt(map.get("${gConfig.group.getGroupName()}${s.getNickName()}points${t.getId()}").split(' ')[0])
                        appendTag(tmp, "td align=\"center\"", "${res.split(' ')[0]}/${t.getPoints()} + ${pointStr.split(' ')[0]}")
                    }
                } else appendTag(tmp, "td align=\"center\"", "0/${t.getPoints()}")
            })
            gConfig.getControlPoints().sort().forEach(c -> {appendTag(tmp,"td align=\"center\"", "${new ControlPChecker(gConfig.group.getGroupName(),s.getNickName(),c,gConfig.getTasks().sort(),map).getResult()}")})

            appendTag(tmp,"td align=\"center\"", "${sumC}/${sumT}")
            appendTag(tmp,"td align=\"center\"", "${sumP}")
            appendTag(apTable,"tr", tmp.toString())
        })

        apTable.append("</table>")
        return apTable.toString()
    }


    String attendance(){
        StringBuilder tmp = new StringBuilder()
        appendTag(tmp,"th", "")
        gConfig.getLessons().sort().forEach(l -> {
            appendTag(tmp,"th", formatter.format(l.getDate()).toString())
        })
        appendTag(tmp,"th", "TOTAL")
        appendTag(attTable,"tr", tmp.toString())


        gConfig.getGroup().getStudents().sort().forEach(s->{
            tmp.setLength(0)
            appendTag(tmp,"th align=\"left\"", "${s.getSurname()} ${s.getName()} ${s.getPatronymic()}")
            int cnt = 0
            gConfig.getLessons().sort().forEach(l -> {
                String res = map.get("${gConfig.group.getGroupName()}${s.getNickName()}attendance${l.getDate()}")
                if(res != null) {
                    if (res == 'true') {
                        cnt++
                        appendTag(tmp, "td align=\"center\"", "+")
                    } else appendTag(tmp, "td align=\"center\"", "н")
                } else appendTag(tmp, "td align=\"center\"", "error")
            })
            appendTag(tmp,"td align=\"center\"", "${cnt}/${gConfig.getLessons().size()}")
            appendTag(attTable,"tr", tmp.toString())
        })

        attTable.append("</table>")
        return attTable.toString()
    }

    private void appendTag(StringBuilder sb, String tag, String contents) {
        sb.append('<').append(tag).append('>')
        sb.append(contents)
        sb.append("</").append(tag).append('>')
    }
    private void appendDataCell(StringBuilder sb, String contents) {
        appendTag(sb, "td", contents)
    }
    private void appendHeaderCell(StringBuilder sb, String contents) {
        appendTag(sb, "th", contents)
    }
}
