package com.example.nsoftZadatak.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "blocked_devices")
public class BlockedDevice {

    @Id
    int id;
    @org.hibernate.annotations.Type(type="uuid-char")
    @Column(name = "uuid", unique = true)
    UUID uuid;

    ZonedDateTime blockedUntil;

    public BlockedDevice() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public ZonedDateTime getBlockedUntil() {
        return blockedUntil;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setBlockedUntil(ZonedDateTime blockedUntil) {
        this.blockedUntil = blockedUntil;
    }
}
