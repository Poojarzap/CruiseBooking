package com.cruisebooking.rest.service;

import com.cruisebooking.rest.model.CruiseModel;
import com.cruisebooking.rest.model.UserModel;

import java.util.List;

public interface CruiseServiceInterface {
    //Cruise Implementations
    public List<CruiseModel> getCruiseList();
    //public CruiseModel getCruiseInfo(String id);
    public CruiseModel createCruiseInfo(CruiseModel cd);


    //User Implementations
    public UserModel createUserInfo(UserModel userInfo);
    public UserModel findUserFromDb(String userPhone);

}
