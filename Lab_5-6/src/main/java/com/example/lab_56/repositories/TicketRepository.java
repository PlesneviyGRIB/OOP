package com.example.lab_56.repositories;

import com.example.lab_56.dto.TicketDTO;
import com.example.lab_56.models.Ticket;
import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends QuerydslJdbcRepository<Ticket, String> {
    @Modifying
    @Query("insert into tickets (ticket_no, book_ref, passenger_id, passenger_name, contact_data) VALUES (:ticket_no, :book_ref, :passenger_id, :passenger_name, to_jsonb(:contact_data))")
    void manualSave(@Param("ticket_no") String ticket_no,
                    @Param("book_ref") String book_ref,
                    @Param("passenger_id") String passenger_id,
                    @Param("passenger_name") String passenger_name,
                    @Param("contact_data") String contact_data);

    @Query("""
        select t.ticket_no as ticketNo, t.book_ref as bookRef, t.passenger_name as name, t.passenger_id as passengerId
        from tickets as t
        where t.passenger_id = :id;
    """)
    List<TicketDTO> getTicketsByPassengerId(@Param("id") String id);

//    @Query("""
//        select t.ticket_no as ticketNo, t.book_ref as bookRef, t.passenger_name as name, t.passenger_id as passengerId
//        from tickets as t
//        where t.ticket_no = :id;
//    """)
//    Optional<TicketDTO> getTicketById(@Param("id") String id);
}
