package com.savchenko.dsl.closure

import com.savchenko.dsl.Student
import com.savchenko.dsl.config.GroupConfiguration
import com.savchenko.dsl.supportive.DateMatcher
import static groovy.lang.Closure.DELEGATE_ONLY

class BuildGroup {
    List<GroupConfiguration> groupConfigurations
    EnvironmentParams env
    Map<String, String> map;

    BuildGroup(EnvironmentParams env, List<GroupConfiguration> groupConfigurations, map){
        this.env = env
        this.groupConfigurations = groupConfigurations
        this.map = map
    }

    class BuildParams {
        GroupConfiguration groupConfiguration;

        BuildParams(GroupConfiguration groupConfiguration){
            this.groupConfiguration = groupConfiguration
        }

        void gitLoader() {
            List<Student> exclude = new ArrayList<>()
            groupConfiguration.getGroup().getStudents().forEach(s->{
                def path = "${env.getDownloadDirectory()}/${s.nickName}"
                Process process = new ProcessBuilder('git', 'clone', '-b', s.getBranchName(), s.getUrl().toString(), path).start()
                def output = new StringWriter(), error = new StringWriter()
                process.waitForProcessOutput(output, error)
                if(!error.toString().contains("fatal:")) {
                    map.put("${groupConfiguration.getGroup().getGroupName()}${s.getNickName()}gitLoader", "LOADED_FROM_GIT")
                    println "${groupConfiguration.getGroup().getGroupName()} ${s.getNickName()} repositoriy ${s.getUrl().toString()} [-b ${s.getBranchName()}] LOADED_FROM_GIT"
                } else if(error.toString().contains("Could not resolve host:")) exclude.add(s)
            })
            exclude.forEach(s->groupConfiguration.getGroup().getStudents().remove(s))
        }

        void assemble() {
            groupConfiguration.getGroup().getStudents().forEach(s->{ groupConfiguration.getTasks().forEach(t ->{
                def path = "${env.getDownloadDirectory()}/${s.nickName}/${t.getId()}"
                    boolean status = processed(new ProcessBuilder("gradle", "assemble"), path)
                    map.put("${groupConfiguration.getGroup().getGroupName()}${s.getNickName()}assemble${t.getId()}", "${status}")
                    println "${groupConfiguration.getGroup().getGroupName()} ${s.getNickName()} ${t.getId()} ASSEMBLE ${status}"
            })})
        }

        void test() {
            groupConfiguration.getGroup().getStudents().forEach(s->{ groupConfiguration.getTasks().forEach(t ->{
                def path = "${env.getDownloadDirectory()}/${s.nickName}/${t.getId()}"
                boolean status = processed(new ProcessBuilder("gradle", "test"), path)
                map.put("${groupConfiguration.getGroup().getGroupName()}${s.getNickName()}test${t.getId()}","${status}")
                println "${groupConfiguration.getGroup().getGroupName()} ${s.getNickName()} ${t.getId()} TEST ${status}"
            })})
        }

        void javaDoc() {
            groupConfiguration.getGroup().getStudents().forEach(s->{ groupConfiguration.getTasks().forEach(t ->{
                def path = "${env.getDownloadDirectory()}/${s.nickName}/${t.getId()}"
                boolean status = processed(new ProcessBuilder("gradle", "javadoc"), path)
                map.put("${groupConfiguration.getGroup().getGroupName()}${s.getNickName()}javaDoc${t.getId()}","${status}")
                println "${groupConfiguration.getGroup().getGroupName()} ${s.getNickName()} ${t.getId()} JAVADOC ${status}"
            })})
        }

        void attendance(){
            groupConfiguration.getGroup().getStudents().forEach(s->{ groupConfiguration.getLessons().forEach(l ->{
                ProcessBuilder processBuilder = new ProcessBuilder('git', 'log', '--pretty=format:\"%ad\"', '--date=format:%d %m %Y')
                processBuilder.directory(new File("${env.getDownloadDirectory()}/${s.nickName}"))
                Process process = processBuilder.start()
                process.waitFor()
                boolean status = new DateMatcher(process.text,l.getDate()).match()
                map.put("${groupConfiguration.getGroup().getGroupName()}${s.getNickName()}attendance${l.getDate()}","${status}")
                println "${groupConfiguration.getGroup().getGroupName()} ${s.getNickName()} ATTENDANCE [${l.getDate()}] ${status}"
            })})
        }

        private boolean processed(ProcessBuilder processBuilder, String path){
            try{
                processBuilder.directory(new File(path))
                Process process = processBuilder.start()
                process.waitFor()
                return parseOut(process.text)
            }catch (Exception ignored){ return false }
        }

        private boolean parseOut(String out){
            return out.contains("BUILD SUCCESSFUL")
        }
    }
    void group(String group, @DelegatesTo(value = BuildParams, strategy = DELEGATE_ONLY) Closure closure){
        if(groupConfigurations.stream().filter(g -> g.getGroup().getGroupName() == group).count() > 0){
            closure.delegate = new BuildParams(groupConfigurations.stream().filter(g -> g.getGroup().getGroupName() == group).toList().get(0))
            closure.resolveStrategy = DELEGATE_ONLY
            closure.call()
        }
    }
}
