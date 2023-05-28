package com.example.lab_56.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("bookings")
public class Booking {
    @Id
    @Column("book_ref")
    private String bookRef;
    @Column("book_date")
    private Date bookDate;
    @Column("total_amount")
    private Long totalAmount;
}
