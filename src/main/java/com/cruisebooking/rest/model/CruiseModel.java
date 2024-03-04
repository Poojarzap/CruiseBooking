package com.cruisebooking.rest.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CruiseDetails")
public class CruiseModel {

    @Id
    private String cruiseId;

    private String cruiseName;
    private String shipName;

    public CruiseModel() {
    }

    public CruiseModel(String cruiseId, String cruiseName, String shipName) {
        this.cruiseId = cruiseId;

        this.cruiseName = cruiseName;
        this.shipName = shipName;
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
}
