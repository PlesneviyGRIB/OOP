package com.savchenko.shop.main.stat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

@Getter @Setter
@ToString
public class Period {
    private LocalDate firstDate;
    private LocalDate secondDate;
}
