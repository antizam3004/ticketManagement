package com.example.nsoftZadatak.repository;

import com.example.nsoftZadatak.model.Config;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
;import javax.transaction.Transactional;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Integer> {

    @Query(value = "select if(count(*) > 0,'true', 'false') as myBool " +
            "from config", nativeQuery = true)
    boolean entryExists();

    @Modifying
    @Transactional
    @Query(value = "update config set time_duration=:timeDuration, stake_limit=:stakeLimit, " +
                    "hot_percentage=:hotPercentage, restriction_expires=:restrictionExpires where 1", nativeQuery = true)
    void configUpdate(@Param("timeDuration")int timeDuration,
                      @Param("stakeLimit")int stakeLimit,
                      @Param("hotPercentage")int hotPercentage,
                      @Param("restrictionExpires")int restrictionExpires);


}
