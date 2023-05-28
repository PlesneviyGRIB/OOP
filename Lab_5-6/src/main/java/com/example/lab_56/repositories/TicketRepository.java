package com.example.lab_56.repositories;

import com.example.lab_56.models.Ticket;
import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends QuerydslJdbcRepository<Ticket, String> {
    @Modifying
    @Query("insert into tickets (ticket_no, book_ref, passenger_id, passenger_name, contact_data) VALUES (:ticket_no, :book_ref, :passenger_id, :passenger_name, to_jsonb(:contact_data))")
    void manualSave(@Param("ticket_no") String ticket_no,
                    @Param("book_ref") String book_ref,
                    @Param("passenger_id") String passenger_id,
                    @Param("passenger_name") String passenger_name,
                    @Param("contact_data") String contact_data);
}
