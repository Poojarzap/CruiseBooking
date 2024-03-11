package com.cruisebooking.rest.controller;

import com.cruisebooking.rest.model.CruiseModel;
import com.cruisebooking.rest.model.UserModel;
import com.cruisebooking.rest.service.CruiseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@RestController

public class CruiseController {
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
                UserModel user = cruiseServiceInterface.findUserFromDb(userPhone);
                if (user != null && user.getUserPassword().equals(userPassword)) {
                    modelAndView.setViewName("userHome");
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
        cruiseServiceInterface.createUserInfo(userModel);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }




//    @GetMapping("{cruiseId}")
//    public CruiseModel getCruiseInfo(@PathVariable("cruiseId") String id) {
//        return cruiseServiceInterface.getCruiseInfo(id);
//    }

    @PostMapping
    public String createCruise(@RequestBody CruiseModel cruiseModel) {
        cruiseServiceInterface.createCruiseInfo(cruiseModel);
        return "Successfull";
    }

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
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

    @PostMapping("/addCruise")
    public ModelAndView addCruise(@ModelAttribute CruiseModel cruiseModel) {
        cruiseServiceInterface.createCruiseInfo(cruiseModel);
        List<CruiseModel> cruiseList = cruiseServiceInterface.getCruiseList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("cruiseList", cruiseList);
        return modelAndView;
    }


//    @PostMapping("/addUser")
//    public ModelAndView addUser(@ModelAttribute UserModel userModel) {
//        cruiseServiceInterface.createUserInfo(userModel);
//        //List<CruiseModel> cruiseList = cruiseServiceInterface.getCruiseList();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("userRegister");
//        modelAndView.addObject("showAlert", true);
//        return modelAndView;
//    }

    @GetMapping("/userHome")
    public ModelAndView userHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userHome");
        return modelAndView;
    }


}
