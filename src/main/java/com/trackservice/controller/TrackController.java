package com.trackservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TrackController {
    @RequestMapping("/")
    public String hello() {
        return "html/index.html";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }
}
