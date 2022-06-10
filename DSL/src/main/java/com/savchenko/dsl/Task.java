package com.savchenko.dsl;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
public class Task {
    private final String id;
    private final int points;
    private final String title;
}
