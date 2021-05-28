package com.example.demo2.controller;

import com.example.demo2.service.FirebaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DoController {

    @Autowired
    FirebaseService firebaseService;

    private static final Logger logger = LoggerFactory.getLogger(DoController.class);

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String ajaxTest() {
        logger.info("Welcome home! Test!{}");

        return "index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
//        logger.info("Welcome home! Test!{}");

        return "home";
    }
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {
//        logger.info("Welcome home! Test!{}");

        return "about";
    }

}
