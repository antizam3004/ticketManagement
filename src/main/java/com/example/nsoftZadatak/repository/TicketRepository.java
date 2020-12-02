package com.example.nsoftZadatak.repository;

import com.example.nsoftZadatak.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {


    @Query(value = "select sum(stake) from ticket where device_id=:deviceID " +
            "and ticket_date_time>=date_sub(now(),interval :brojSekundi second)", nativeQuery = true)
    double getSum(@Param("deviceID") String deviceID, @Param("brojSekundi") int brojSekundi );
}


