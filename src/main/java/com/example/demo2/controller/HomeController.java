package com.example.demo2.controller;

import com.example.demo2.entity.Board;
import com.example.demo2.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;


@RestController
@RequestMapping("/next")
public class HomeController {

    @Autowired
    FirebaseService firebaseService;

    @PostMapping("/the")
    public String insertId(@RequestBody Board id) throws Exception{
        return firebaseService.insertId(id);
    }

}
