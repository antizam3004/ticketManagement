package com.example.nsoftZadatak.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "config")
public class Config {

    @Id
    private int id;

    private int timeDuration;
    private int stakeLimit;
    private int hotPercentage;
    private int restrictionExpires;

    public Config() {
    }

    public int getTimeDuration() {
        return timeDuration;
    }

    public int getStakeLimit() {
        return stakeLimit;
    }

    public int getHotPercentage() {
        return hotPercentage;
    }

    public int getRestrictionExpires() {
        return restrictionExpires;
    }

    public void setTimeDuration(int timeDuration) {
        this.timeDuration = timeDuration;
    }

    public void setStakeLimit(int stakeLimit) {
        this.stakeLimit = stakeLimit;
    }

    public void setHotPercentage(int hotPercentage) {
        this.hotPercentage = hotPercentage;
    }

    public void setRestrictionExpires(int restrictionExpires) {
        this.restrictionExpires = restrictionExpires;
    }
}
