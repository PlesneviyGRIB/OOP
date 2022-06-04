package com.savchenko.controlsystem.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class Task {
    private final String id;
    private final int points;
    private final String title;
}
