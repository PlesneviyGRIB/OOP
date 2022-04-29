package com.savchenko.shop.models;

import lombok.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class Purchase {
    private transient int id;
    private int customerId;
    private int productId;
    private Date date;
}
