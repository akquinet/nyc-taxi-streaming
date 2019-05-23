package com.akquinet.pipeline.streaming.Model;

import com.fasterxml.jackson.annotation.*;
import lombok.NoArgsConstructor;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@JsonRootName(value = "cookedTaxi")
@CsvRecord( separator = ",")
public class TaxiRide implements Serializable {

    @JsonProperty("key")
    @DataField(pos = 1)
    private String key;
    @JsonProperty("fareAmount")
    @DataField(pos = 2)
    private double fareAmount;
    @DataField(pos = 3, pattern = "yyyy-MM-dd HH:mm:ss 'UTC'", timezone = "UTC")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private Date pickupDate;
    @JsonProperty("pickupLong")
    @DataField(pos = 4)
    private float pickupLong;
    @JsonProperty("pickupLat")
    @DataField(pos = 5)
    private float pickupLat;
    @JsonProperty("dropoffLong")
    @DataField(pos = 6)
    private float dropoffLong;
    @JsonProperty("dropoffLat")
    @DataField(pos = 7)
    private float dropoffLat;
    @JsonProperty("passengerCount")
    @DataField(pos = 8)
    private int passengerCount;

    @Override
    public String toString() {
        return "TaxiRide{" +
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
