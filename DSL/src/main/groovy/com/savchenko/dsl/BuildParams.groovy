package com.savchenko.dsl

import com.savchenko.dsl.enums.Scope

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
                def path = env.getDownloadDirectory() + '/' + s.nickName
                processed(new ProcessBuilder('git', 'clone', '-b', s.getBranchName(), s.getUrl().toString(), path), path)
            })
        }

        void assemble() {

        }

        void test() {

        }

        void javaDoc(Scope scope) {
            groupConfiguration.getGroup().getStudents().forEach(s->{ groupConfiguration.getIncomingTasks().forEach(t ->{
                def path = env.getDownloadDirectory() + '/' + s.nickName + '/' + t.getId()
                processed(new ProcessBuilder("gradle", "javadoc"), path)
            })})
        }

        void syntaxCheck() {

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
