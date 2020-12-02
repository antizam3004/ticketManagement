package com.example.nsoftZadatak.repository;

import com.example.nsoftZadatak.model.BlockedDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Repository
public interface BlockedDevicesRepository extends JpaRepository<BlockedDevice, Integer> {

    @Query(value = "select if(count(*) > 0,'true', 'false') as myBool " +
                    "from blocked_devices " +
                    "where uuid = :deviceId and blocked_until > now()", nativeQuery = true)
    boolean isBlocked(@Param("deviceId") String deviceId);

    @Modifying
    @Transactional
    @Query(value =  "UPDATE blocked_devices " +
                    "SET blocked_until = date_add(now(), interval :brojSekundi second) " +
                    "WHERE uuid=:deviceId", nativeQuery = true)
    void updateBlockedDevice(@Param("deviceId") String deviceId, @Param("brojSekundi") int sekunde);
}
