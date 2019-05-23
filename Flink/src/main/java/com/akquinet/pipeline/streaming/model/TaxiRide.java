package com.akquinet.pipeline.streaming.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@JsonRootName(value = "cookedTaxi")
public class TaxiRide implements Serializable {

    @JsonProperty("key")
    private String key;
    @JsonProperty("fareAmount")
    private double fareAmount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private Date pickupDate;
    @JsonProperty("pickupLong")
    private float pickupLong;
    @JsonProperty("pickupLat")
    private float pickupLat;
    @JsonProperty("dropoffLong")
    private float dropoffLong;
    @JsonProperty("dropoffLat")
    private float dropoffLat;
    @JsonProperty("passengerCount")
    private int passengerCount;

    @Override
    public String toString() {
        return "{" +
                "key='" + key + '\'' +
                ", fareAmount=" + fareAmount +
                ", pickupDate=" + pickupDate +
                ", pickupLong=" + pickupLong +
                ", pickupLat=" + pickupLat +
                ", dropoffLong=" + dropoffLong +
                ", dropoffLat=" + dropoffLat +
                ", passengerCount=" + passengerCount +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public double getFareAmount() {
        return fareAmount;
    }

    public void setFareAmount(final double fareAmount) {
        this.fareAmount = fareAmount;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(final Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public float getPickupLong() {
        return pickupLong;
    }

    public void setPickupLong(final float pickupLong) {
        this.pickupLong = pickupLong;
    }

    public float getPickupLat() {
        return pickupLat;
    }

    public void setPickupLat(final float pickupLat) {
        this.pickupLat = pickupLat;
    }

    public float getDropoffLong() {
        return dropoffLong;
    }

    public void setDropoffLong(final float dropoffLong) {
        this.dropoffLong = dropoffLong;
    }

    public float getDropoffLat() {
        return dropoffLat;
    }

    public void setDropoffLat(final float dropoffLat) {
        this.dropoffLat = dropoffLat;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(final int passengerCount) {
        this.passengerCount = passengerCount;
    }
}
