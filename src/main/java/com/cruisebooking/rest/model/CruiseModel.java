package com.cruisebooking.rest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CruiseDetails")
public class CruiseModel {
    @Id
    private String _id;
    private String cruiseName;
    private String shipName;

    public CruiseModel() {
    }

    public CruiseModel(String _id, String cruiseName, String shipName) {
        this._id = _id;
        this.cruiseName = cruiseName;
        this.shipName = shipName;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
