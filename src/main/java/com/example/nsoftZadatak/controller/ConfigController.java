package com.example.nsoftZadatak.controller;

import com.example.nsoftZadatak.model.Config;
import com.example.nsoftZadatak.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "stakelimitservice/configuration")
public class ConfigController {

    @Autowired
    ConfigRepository configRepository;

    @PutMapping
    public String configuration(@RequestBody Config config) {

        //ako redak postoji u tablici config
        if(configRepository.entryExists())
        {
            try {
                configRepository.configUpdate(config.getTimeDuration(), config.getStakeLimit(), config.getHotPercentage(), config.getRestrictionExpires());
            }
            catch (Exception e) {
                return "Data integrity violation!\n" +
                        "time_duration[ 300 - 86400 ]\n" +
                        "stake_limit[ 1 - 10000000 ]\n" +
                        "hot_percentage[ 1 - 100 ]\n" +
                        "restriction_expires [ 0 , 60 - ]";
            }
            }
        else //ako je prazna tablica
        {
            configRepository.save(config);
        }
        return "Configuration updated";
    }

}
