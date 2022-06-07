package com.savchenko.controlsystem.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@ToString
public class Lesson {
    private final Date date;
}
