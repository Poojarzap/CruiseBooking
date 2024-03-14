package com.cruisebooking.rest.controller;

import ch.qos.logback.classic.net.SyslogAppender;
import com.cruisebooking.rest.model.BookingIdCounter;
import com.cruisebooking.rest.model.BookingModel;
import com.cruisebooking.rest.model.CruiseModel;
import com.cruisebooking.rest.model.UserModel;
import com.cruisebooking.rest.service.CruiseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class CruiseController {
    public static UserModel user;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Value("${authentication.phoneNumber}")
    private String validPhoneNumber;

    @Value("${authentication.password}")
    private String validPassword;
    @Autowired
    CruiseServiceInterface cruiseServiceInterface;

    public CruiseController(CruiseServiceInterface cruiseServiceInterface) {
        this.cruiseServiceInterface = cruiseServiceInterface;
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String userPhone, @RequestParam String userPassword) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            if (validPhoneNumber.equals(userPhone) && validPassword.equals(userPassword)) {
                modelAndView.setViewName("home");
            } else {
                user = cruiseServiceInterface.findUserFromDb(userPhone);
//                System.out.println(user.getName());
                if (user != null && user.getUserPassword().equals(userPassword)) {
                    modelAndView.setViewName("userHome");
                    modelAndView.addObject("userInfo", user);
                }
            }
        } catch (Exception e) {
            modelAndView.setViewName("index");
            modelAndView.addObject("errorMessage", "Invalid phone number or password. Please try again.");
            modelAndView.addObject("showAlert", true);
        }
        return modelAndView;
    }

    @PostMapping("/userRegister")
    public ModelAndView userRegister(@ModelAttribute UserModel userModel) {
        ModelAndView modelAndView = new ModelAndView();
        // Check if the phone number already exists in the database
        UserModel user = cruiseServiceInterface.findUserFromDb(userModel.getUserPhone());
        if (user != null) {
            modelAndView.setViewName("index");
            modelAndView.addObject("errorMessage", "Phone number already exists. Please enter a different phone number.");
            modelAndView.addObject("showAlert", true);
        } else {
            cruiseServiceInterface.createUserInfo(userModel);
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }


//    @GetMapping("{cruiseId}")
//    public CruiseModel getCruiseInfo(@PathVariable("cruiseId") String id) {
//        return cruiseServiceInterface.getCruiseInfo(id);
//    }

//    @PostMapping
//    public String createCruise(@RequestBody CruiseModel cruiseModel) {
//        cruiseServiceInterface.createCruiseInfo(cruiseModel);
//        return "Successfull";
//    }

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @PostMapping("/addCruise")
    public ModelAndView addCruise(@ModelAttribute CruiseModel cruiseModel) {
        cruiseServiceInterface.createCruiseInfo(cruiseModel);
        List<CruiseModel> cruiseList = cruiseServiceInterface.getCruiseList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("cruiseList", cruiseList);
        return modelAndView;
    }
    @GetMapping("/getCruiseList")
    public ModelAndView getCruiseList() {
        List<CruiseModel> cruiseList = cruiseServiceInterface.getCruiseList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("cruiseList", cruiseList);
        return modelAndView;
    }



//    @GetMapping("/userHome")
//    public ModelAndView userHome() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("userHome");
////        System.out.println(user.getName()+"-------");
//        return modelAndView;
//    }

    @GetMapping("/search")
    public ModelAndView detailsofShip(@RequestParam String source,@RequestParam String destination){
        List<CruiseModel> searchResults = cruiseServiceInterface.searchCruises(source, destination);
        // Create a ModelAndView and add the search results to it
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userHome");
        System.out.println(user.getName());
        modelAndView.addObject("searchResults", searchResults);
        modelAndView.addObject("userInfo",user);
        modelAndView.addObject("bookingModel", new BookingModel());
        return modelAndView;
    }

    @GetMapping("/searchByPrice")
    public ModelAndView searchByPrice(@RequestParam double startPrice,@RequestParam double endPrice) {
        List<CruiseModel> searchResults= cruiseServiceInterface.searchCruisesByPriceRange(startPrice,endPrice);
        System.out.println(searchResults);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userHome");
        System.out.println(user.getName());
        modelAndView.addObject("searchResults", searchResults);
        modelAndView.addObject("userInfo",user);
        modelAndView.addObject("bookingModel", new BookingModel());
        return modelAndView;
    }

    @PostMapping("/bookCruise")
    public ModelAndView bookCruise(@ModelAttribute BookingModel bookingModel) {
        int generatedBookingId = generateBookingId();
        bookingModel.setBookingId(String.valueOf(generatedBookingId));
        // Save the booking details to the database
        cruiseServiceInterface.createBookInfo(bookingModel);
        ModelAndView md = new ModelAndView();
        md.setViewName("userHome");
        return md;
    }

    private int generateBookingId() {
        // Use MongoDB's findAndModify to atomically increment the booking ID
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is("bookingIdCounter"));
        Update update = new Update().inc("sequence", 1);
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);
        BookingIdCounter counter = mongoTemplate.findAndModify(query, update, options, BookingIdCounter.class);
        return counter.getSequence();
    }


    @GetMapping("/bookings")
    public ModelAndView bookingResult(){
//        String bookingUser= user.getUserPhone();
//        List<BookingModel> bookingModelList=cruiseServiceInterface.getBookingInfo(bookingUser);
//        List<CruiseModel> cruiseList=new ArrayList<>();
//        List<UserModel> userList=new ArrayList<>();
//
//
//        for(int i=0;i<bookingModelList.size();i++) {
//            BookingModel bd=bookingModelList.get(i);
//            cruiseList.add(cruiseServiceInterface.findCruiseById(bd.getBookingCruise()));
//            userList.add(cruiseServiceInterface.findUserFromDb(bd.getBookingUser()));
//        }
//        System.out.println(bookingModelList);
//        System.out.println(cruiseList);
//        System.out.println(userList);
//
//
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.addObject("bookingResult",bookingModelList);
//        modelAndView.addObject("cruiseResult",cruiseList);
//        modelAndView.addObject("userResult",userList);
//        modelAndView.setViewName("userHome");
//        return modelAndView;
//    }
        String bookingUser = user.getUserPhone();
        List<BookingModel> bookingModelList = cruiseServiceInterface.getBookingInfo(bookingUser);
        List<CruiseModel> cruiseList = new ArrayList<>();
        List<UserModel> userList = new ArrayList<>();
        List<Map<String, Object>> combinedDataList = new ArrayList<>();

        for (int i = 0; i < bookingModelList.size(); i++) {
            BookingModel bookingModel = bookingModelList.get(i);
            CruiseModel cruiseModel = cruiseServiceInterface.findCruiseById(bookingModel.getBookingCruise());
            UserModel userModel = cruiseServiceInterface.findUserFromDb(bookingModel.getBookingUser());

            cruiseList.add(cruiseModel);
            userList.add(userModel);

            // Create a Map to represent combined data
            Map<String, Object> combinedData = new HashMap<>();
            combinedData.put("bookingModel", bookingModel);
            combinedData.put("cruiseModel", cruiseModel);
            combinedData.put("userModel", userModel);
            // Add the Map to the list
            combinedDataList.add(combinedData);
        }

// Print or use the combinedDataList as needed
        System.out.println(combinedDataList);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("combinedProject",combinedDataList);
        modelAndView.setViewName("userHome");
        return modelAndView;
    }

    @DeleteMapping()
    public ModelAndView cancelBooking(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("userHome");
        return modelAndView;
    }

    @DeleteMapping("/cancel-booking/{bookingId}")
    public ModelAndView cancelBooking(@PathVariable("bookingId") String bookingId) {
        System.out.println(bookingId);
        cruiseServiceInterface.cancelBookingById(bookingId);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("userHome");
        return modelAndView;

    }
}
