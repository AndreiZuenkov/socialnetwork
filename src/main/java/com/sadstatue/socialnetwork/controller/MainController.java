package com.sadstatue.socialnetwork.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {


    @GetMapping()
    public String getTestMessage (){

        return "hello world";
    }
}
