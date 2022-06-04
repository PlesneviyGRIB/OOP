package com.savchenko.controlsystem.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.Date;

@RequiredArgsConstructor
@Getter
public class IncomingTask {
    private final String id;
    private final Date date;
}
