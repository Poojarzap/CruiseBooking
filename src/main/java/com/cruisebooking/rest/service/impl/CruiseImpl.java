package com.cruisebooking.rest.service.impl;

import com.cruisebooking.rest.model.BookingModel;
import com.cruisebooking.rest.model.CruiseModel;
import com.cruisebooking.rest.model.UserModel;
import com.cruisebooking.rest.repository.BookRepository;
import com.cruisebooking.rest.repository.CruiseRepository;
import com.cruisebooking.rest.repository.UserRepository;
import com.cruisebooking.rest.service.CruiseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CruiseImpl implements CruiseServiceInterface {

    @Autowired
    CruiseRepository cruiseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;



    @Override
    public List<CruiseModel> getCruiseList() {
        return cruiseRepository.findAll();
    }
    @Override
    public CruiseModel createCruiseInfo(CruiseModel cd) {
        return cruiseRepository.save(cd);
    }

    @Override
    public CruiseModel findCruiseById(String cruiseId) {
        return cruiseRepository.findById(cruiseId).get();
    }


    @Override
    public UserModel createUserInfo(UserModel userInfo) {
        return userRepository.save(userInfo);
    }

    @Override
    public UserModel findUserFromDb(String userPhone) {
        return userRepository.findById(userPhone).get();
    }

    @Override
    public List<CruiseModel> searchCruises(String source, String destination) {
        // Implement the logic to search for cruises in the database
        return cruiseRepository.findBySourceAndDestination(source, destination);
    }
    @Override
    public List<CruiseModel> searchCruisesByPriceRange(double startPrice, double endPrice) {
            return cruiseRepository.findByPriceBetween(startPrice,endPrice);
    }

//    @Override
//    public int getLatestBookingId() {
//        BookingModel latestBooking = bookRepository.findTopByOrderByBookingIdDesc();
//        return (latestBooking != null && latestBooking.getBookingId() != null) ? Integer.parseInt(latestBooking.getBookingId()) : 0;
//    }
    @Override
    public BookingModel createBookInfo(BookingModel bookingModel) {
        return bookRepository.save(bookingModel);
    }

    @Override
    public List<BookingModel> getBookingInfo(String bookingUser) {
        return bookRepository.findAllByBookingUser(bookingUser);
    }

    @Override
    public Boolean cancelBookingById(String bookingId) {
        bookRepository.deleteById(bookingId);
        return true;
    }

}
