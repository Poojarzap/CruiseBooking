package com.cruisebooking.rest.controller;

import com.cruisebooking.rest.model.CruiseModel;
import com.cruisebooking.rest.service.CruiseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/cruise")
public class CruiseController {
    public CruiseController(CruiseServiceInterface cruiseServiceInterface) {
        this.cruiseServiceInterface = cruiseServiceInterface;
    }

    @Autowired
    CruiseServiceInterface cruiseServiceInterface;
    @GetMapping()
    public List<CruiseModel> getCruiseList(){
        return cruiseServiceInterface.getCruiseList();
    }

    @GetMapping("{cruiseId}")
    public CruiseModel getCruiseInfo(@PathVariable("cruiseId")String id){
        return cruiseServiceInterface.getCruiseInfo(id);
    }

    @PostMapping
    public String createCruise(@RequestBody CruiseModel cruiseModel)
    {
        cruiseServiceInterface.createCruiseInfo(cruiseModel);
        return "Successfull";
    }

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }
//    @GetMapping("/home")
//    public String home(){
//        return "home";
//    }
//
// @PostMapping("/cruisedetails")
//   public String cruiseDetails(){
//     return "home";
//   }



}
