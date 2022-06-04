package com.savchenko.controlsystem.models;

import lombok.*;
import java.net.URL;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Student {
    private final String nickName;
    private final URL url;
    private final String surname;
    private final String name;
    private final String patronymic;
    @Setter
    private String branchName = "master";
}