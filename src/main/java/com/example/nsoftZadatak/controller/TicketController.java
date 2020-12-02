package com.example.nsoftZadatak.controller;

import com.example.nsoftZadatak.model.BlockedDevice;
import com.example.nsoftZadatak.model.Config;
import com.example.nsoftZadatak.model.Ticket;
import com.example.nsoftZadatak.repository.BlockedDevicesRepository;
import com.example.nsoftZadatak.repository.ConfigRepository;
import com.example.nsoftZadatak.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "stakelimitservice/tickets")
public class TicketController {

    @Autowired
    BlockedDevicesRepository blockedDevicesRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ConfigRepository configRepository;

    @PostMapping
    public String addTicket(@RequestBody Ticket ticket){

        //ako je već blokiran
        Instant instant= Instant.now();
        ZoneId zoneId=ZoneId.of("Europe/Zagreb");
        ZonedDateTime zonedDateTime=ZonedDateTime.ofInstant(instant,zoneId);

        if(blockedDevicesRepository.isBlocked(ticket.getDeviceId().toString())){
            System.out.println("jeste blokiran");
            return "{\"status\" : \"BLOCKED\"}";
        }

        //insert ticketa u bazu
        ticketRepository.save(ticket);

        Config config=configRepository.findAll().get(0);

        int timeDuration=config.getTimeDuration();
        double stakeLimit=config.getStakeLimit();
        double hotPercentage=config.getHotPercentage()/100d;
        int restrictionExpires=config.getRestrictionExpires();

        double sum=ticketRepository.getSum(ticket.getDeviceId().toString(),timeDuration);

        String status="OK";

        if(sum>=stakeLimit*hotPercentage && sum<stakeLimit){
            status="HOT";
        }
        else if(sum>stakeLimit){
            //dodajemo deviceId u tablicu blokiranih


            //ako je postavljeno 0 znači da je restriction_expires=never
            //zadajemo neki broj sekunda za restrikciju od današnjeg datuma, cca 30 godina
            if(restrictionExpires==0){
                restrictionExpires=999999999;
            }


            try {
                Optional<BlockedDevice> blockedDevice;
                blockedDevice=blockedDevicesRepository.findAll()
                        .stream()
                        .filter(blockedDevice1->ticket.getDeviceId().equals(blockedDevice1.getUuid()))
                        .findFirst();
                if(blockedDevice.isPresent()) {
                    blockedDevicesRepository.updateBlockedDevice(String.valueOf(ticket.getDeviceId()), config.getTimeDuration());
                }
                else{
                    BlockedDevice newBlockedDevice=new BlockedDevice();
                    newBlockedDevice.setUuid(ticket.getDeviceId());
                    newBlockedDevice.setBlockedUntil(zonedDateTime);
                    blockedDevicesRepository.save(newBlockedDevice);
                }
            }
            catch (DataIntegrityViolationException e){
                return "{\"status\" : \"BLOCKED\"}";
            }

            status="BLOCKED";
        }

        return "{\"status\" : \""+status+"\"}";


    }

    @GetMapping
    public List<Ticket> getAll(){
        return ticketRepository.findAll();
    }





}
