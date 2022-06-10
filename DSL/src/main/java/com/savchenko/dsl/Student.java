package com.savchenko.dsl;

import lombok.*;
import java.net.URL;

@Data
public class Student {
    private final String nickName;
    private final URL url;
    private final String surname;
    private final String name;
    private final String patronymic;

    @SneakyThrows
    public Student(String nickName, String url, String surname, String name, String patronymic, String branchName) {
        this.nickName = nickName;
        this.url = new URL(url);
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.branchName = branchName;
    }

    @SneakyThrows
    public Student(String nickName, String url, String surname, String name, String patronymic) {
        this.nickName = nickName;
        this.url = new URL(url);
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }
    @Setter
    private String branchName = "master";
}