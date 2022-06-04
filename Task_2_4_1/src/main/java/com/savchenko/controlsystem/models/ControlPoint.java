package com.savchenko.controlsystem.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.Date;

@RequiredArgsConstructor
@Getter
public class ControlPoint {
    private final String controlPointName;
    private final Date date;
}
