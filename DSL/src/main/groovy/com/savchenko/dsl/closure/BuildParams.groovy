package com.savchenko.dsl.closure

import com.savchenko.dsl.config.GroupConfiguration
import com.savchenko.dsl.supportive.DateMatcher
import static groovy.lang.Closure.DELEGATE_ONLY

class BuildGroup {
    List<GroupConfiguration> groupConfigurations
    EnvironmentParams env


    BuildGroup(EnvironmentParams env, List<GroupConfiguration> groupConfigurations){
        this.env = env
        this.groupConfigurations = groupConfigurations
    }

    class BuildParams {
        GroupConfiguration groupConfiguration;

        BuildParams(GroupConfiguration groupConfiguration){
            this.groupConfiguration = groupConfiguration
        }

        void gitLoader() {
            groupConfiguration.getGroup().getStudents().forEach(s->{
                def path = "${env.getDownloadDirectory()}/${s.nickName}"
                new ProcessBuilder('git', 'clone', '-b', s.getBranchName(), s.getUrl().toString(), path).start().waitFor()
                println "${groupConfiguration.getGroup().getGroupName()} ${s.getNickName()} repositoriy ${s.getUrl().toString()} [-b ${s.getBranchName()}] LOADED_FROM_GIT"
            })
        }

        void assemble() {
            groupConfiguration.getGroup().getStudents().forEach(s->{ groupConfiguration.getTasks().forEach(t ->{
                def path = "${env.getDownloadDirectory()}/${s.nickName}/${t.getId()}"
                boolean status = processed(new ProcessBuilder("gradle", "assemble"), path)
                println "${groupConfiguration.getGroup().getGroupName()} ${s.getNickName()} ${t.getId()} ASSEMBLE ${status}"
            })})
        }

        void test() {
            groupConfiguration.getGroup().getStudents().forEach(s->{ groupConfiguration.getIncomingTasks().forEach(t ->{
                def path = "${env.getDownloadDirectory()}/${s.nickName}/${t.getId()}"
                boolean status = processed(new ProcessBuilder("gradle", "test"), path)
                println "${groupConfiguration.getGroup().getGroupName()} ${s.getNickName()} ${t.getId()} TEST ${status}"
            })})
        }

        void javaDoc() {
            groupConfiguration.getGroup().getStudents().forEach(s->{ groupConfiguration.getTasks().forEach(t ->{
                def path = "${env.getDownloadDirectory()}/${s.nickName}/${t.getId()}"
                boolean status = processed(new ProcessBuilder("gradle", "javadoc"), path)
                println "${groupConfiguration.getGroup().getGroupName()} ${s.getNickName()} ${t.getId()} JAVADOC ${status}"
            })})
        }

        void attendance(){
            groupConfiguration.getGroup().getStudents().forEach(s->{ groupConfiguration.getLessons().forEach(l ->{
                Process process = new ProcessBuilder('git', 'log', '--pretty=format:\"%ad\"', '--date=format:%d %m %Y').start()
                process.waitFor()
                boolean status = new DateMatcher(process.text,l.getDate()).match()
                println "${groupConfiguration.getGroup().getGroupName()} ${s.getNickName()} ATTENDANCE [${l.getDate()}] ${status}"
            })})
        }

        private boolean processed(ProcessBuilder processBuilder, String path){
            processBuilder.directory(new File(path))
            Process process = processBuilder.start()
            process.waitFor()
            return parseOut(process.text)
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
