package com.example.lab_56.repositories;

import com.example.lab_56.models.Booking;
import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface BookingRepository extends QuerydslJdbcRepository<Booking, String> {

    @Modifying
    @Query("insert into bookings (book_ref, book_date, total_amount) VALUES (:book_ref, :book_date, :total_amount)")
    void manualSave(@Param("book_ref") String book_ref,
                    @Param("book_date") Date book_date,
                    @Param("total_amount") Long total_amount);
}
