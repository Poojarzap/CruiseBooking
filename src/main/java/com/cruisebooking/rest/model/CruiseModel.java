package com.cruisebooking.rest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "CruiseDetails")
public class CruiseModel {

    @Id
    private String cruiseId;
    private String cruiseName;
    private String shipName;
    private String source;
    private String destination;
    private double price;
    private int totalSeats;
    private int availableSeats;

    public CruiseModel() {
    }
    public CruiseModel(String cruiseId, String cruiseName, String shipName,String source,String destination,double price,int totalSeats,int availableSeats) {
        this.cruiseId = cruiseId;
        this.cruiseName = cruiseName;
        this.shipName = shipName;
        this.source=source;
        this.destination=destination;
        this.price=price;
        this.totalSeats=totalSeats;
        this.availableSeats=availableSeats;
    }

    public String getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(String cruiseId) {
        this.cruiseId = cruiseId;
    }


    public String getCruiseName() {
        return cruiseName;
    }

    public void setCruiseName(String cruiseName) {
        this.cruiseName = cruiseName;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public int getTotalSeats() { return totalSeats; }
    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }

    public int getAvailableSeats() { return availableSeats; }

    public void setAvailableSeats(int availableSeats)  { this.availableSeats = availableSeats;}

}
