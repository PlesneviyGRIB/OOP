package com.example.lab_56.repositories;

import com.example.lab_56.models.BoardingPass;
import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface BoardingPassRepository extends QuerydslJdbcRepository<BoardingPass, Long> {
    @Modifying
    @Query("insert into boarding_passes (ticket_no, flight_id, boarding_no, seat_no) VALUES (:ticket_no, :flight_id, :boarding_no, :seat_no)")
    void manualSave(@Param("ticket_no") String ticket_no,
                    @Param("flight_id") Long flight_id,
                    @Param("boarding_no") Integer boarding_no,
                    @Param("seat_no") String seat_no);

}
