package com.cruisebooking.rest.service.impl;

import ch.qos.logback.classic.net.SyslogAppender;
import com.cruisebooking.rest.model.CruiseModel;
import com.cruisebooking.rest.model.UserModel;
import com.cruisebooking.rest.repository.CruiseRepository;
import com.cruisebooking.rest.repository.UserRepository;
import com.cruisebooking.rest.service.CruiseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;

@Service
public class CruiseImpl implements CruiseServiceInterface {
    @Autowired
    CruiseRepository cruiseRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<CruiseModel> getCruiseList() {
        return cruiseRepository.findAll();
    }
    //@Override
//    public CruiseModel getCruiseInfo(String id) {
//        return cruiseRepository.findById(id).get();
//    }
    @Override
    public CruiseModel createCruiseInfo(CruiseModel cd) {
        return cruiseRepository.save(cd);
    }
    @Override
    public UserModel createUserInfo(UserModel userInfo) {
        return userRepository.save(userInfo);
    }

    @Override
    public List<CruiseModel> searchCruises(String source, String destination) {
        // Implement the logic to search for cruises in the database
        return cruiseRepository.findBySourceAndDestination(source, destination);
    }

    @Override
    public List<CruiseModel> searchCruisesByPriceRange(String startPrice, String endPrice) {
        return cruiseRepository.findByPriceBetween(startPrice, endPrice);
    }


    @Override
    public UserModel findUserFromDb(String userPhone) {
        return userRepository.findById(userPhone).get();
    }



}
