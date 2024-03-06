package com.cruisebooking.rest.controller;

import com.cruisebooking.rest.model.CruiseModel;
import com.cruisebooking.rest.model.UserModel;
import com.cruisebooking.rest.service.CruiseServiceInterface;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/cruise")
public class CruiseController {
    @Autowired
    CruiseServiceInterface cruiseServiceInterface;
    public CruiseController(CruiseServiceInterface cruiseServiceInterface) {
        this.cruiseServiceInterface = cruiseServiceInterface;
    }
//    @GetMapping()
//    public List<CruiseModel> getCruiseList(){
//        return cruiseServiceInterface.getCruiseList();
//    }

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


//    @GetMapping("/userRegister")
//    public ModelAndView userRegister() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("userRegister");
//        return modelAndView;
//    }
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
