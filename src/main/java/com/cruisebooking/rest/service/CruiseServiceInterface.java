package com.cruisebooking.rest.service;

import com.cruisebooking.rest.model.CruiseModel;

import java.util.List;

public interface CruiseServiceInterface {
    public List<CruiseModel> getCruiseList();
    public CruiseModel getCruiseInfo(String id);
    public CruiseModel createCruiseInfo(CruiseModel cd);
}
