package com.example.nsoftZadatak.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "ticket")
public class Ticket {


    @Id
    private int id_PK;

    @org.hibernate.annotations.Type(type="uuid-char")
    private UUID id;

    @org.hibernate.annotations.Type(type="uuid-char")
    private UUID deviceId;

    private double stake;

    private Date ticket_date_time=new Date();

    public Ticket() {
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(UUID deviceId) {
        this.deviceId = deviceId;
    }

    public double getStake() {
        return stake;
    }

    public void setStake(double stake) {
        this.stake = stake;
    }
}
