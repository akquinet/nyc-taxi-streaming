package com.akquinet.pipeline.streaming.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.NoArgsConstructor;
import org.elasticsearch.common.geo.GeoPoint;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@JsonRootName(value = "EnrichedRide")
public class EnrichedRide implements Serializable {
    @JsonProperty("key")
    private String key;
    @JsonProperty("fareAmount")
    private double fareAmount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private Date pickupDate;
    @JsonProperty("passengerCount")
    private int passengerCount;
    @JsonProperty("pickupCoord")
    private GeoPoint pickupGeoPoint;
    @JsonProperty("dropoffCoord")
    private GeoPoint dropoffGeoPoint;

    public EnrichedRide(TaxiRide ride) {
        this.key = ride.getKey();
        this.fareAmount = ride.getFareAmount();
        this.passengerCount = ride.getPassengerCount();
        this.pickupDate = ride.getPickupDate();
        this.pickupGeoPoint = new GeoPoint(ride.getPickupLat(), ride.getPickupLong());
        this.dropoffGeoPoint = new GeoPoint(ride.getDropoffLat(), ride.getDropoffLong());
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

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(final int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public GeoPoint getPickupGeoPoint() {
        return pickupGeoPoint;
    }

    public void setPickupGeoPoint(final GeoPoint pickupGeoPoint) {
        this.pickupGeoPoint = pickupGeoPoint;
    }

    public GeoPoint getDropoffGeoPoint() {
        return dropoffGeoPoint;
    }

    public void setDropoffGeoPoint(final GeoPoint dropoffGeoPoint) {
        this.dropoffGeoPoint = dropoffGeoPoint;
    }

    @Override
    public String toString() {
        return "EnrichedRide{" +
                "key='" + key + '\'' +
                ", fareAmount=" + fareAmount +
                ", pickupDate=" + pickupDate +
                ", passengerCount=" + passengerCount +
                ", pickupGeoPoint=" + pickupGeoPoint +
                ", dropoffGeoPoint=" + dropoffGeoPoint +
                '}';
    }
}
