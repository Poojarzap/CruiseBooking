package com.cruisebooking.rest.service.impl;

import com.cruisebooking.rest.model.CruiseModel;
import com.cruisebooking.rest.repository.CruiseRepository;
import com.cruisebooking.rest.service.CruiseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CruiseImpl implements CruiseServiceInterface {
    @Autowired
    CruiseRepository cruiseRepository;
    @Override
    public List<CruiseModel> getCruiseList() {
        return cruiseRepository.findAll();
    }


    @Override
    public CruiseModel getCruiseInfo(String id) {
        return cruiseRepository.findById(id).get();
    }

    @Override
    public CruiseModel createCruiseInfo(CruiseModel cd) {
        return cruiseRepository.save(cd);
    }
}
