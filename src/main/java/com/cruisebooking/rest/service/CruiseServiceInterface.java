package com.cruisebooking.rest.service;

import com.cruisebooking.rest.model.BookingModel;
import com.cruisebooking.rest.model.CruiseModel;
import com.cruisebooking.rest.model.UserModel;

import java.util.List;

public interface CruiseServiceInterface {
    //Cruise Implementations
    public List<CruiseModel> getCruiseList();
    //public CruiseModel getCruiseInfo(String id);
    public CruiseModel createCruiseInfo(CruiseModel cd);
    public CruiseModel findCruiseById(String cruiseId);


    //User Implementations
    public UserModel createUserInfo(UserModel userInfo);
    public UserModel findUserFromDb(String userPhone);
    List<CruiseModel> searchCruises(String source, String destination);
    List<CruiseModel> searchCruisesByPriceRange(double startPrice, double endPrice);



    public BookingModel createBookInfo(BookingModel bookingModel);
    public List<BookingModel> getBookingInfo(String bookingUser);
    public List<BookingModel> getAllBookings();

    public Boolean cancelBookingById(String bookingId);

}
