package com.savchenko.controlsystem.readers;

public class CommentsChecker {
    public static boolean check(String str){
        if(str.trim().matches("^#.*")) return true;
        if(str.trim().length() == 0) return true;
        return false;
    }
}
