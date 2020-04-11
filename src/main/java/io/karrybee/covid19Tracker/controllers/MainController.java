package io.karrybee.covid19Tracker.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller//Render an html ui
public class MainController {

@GetMapping("/")
    public String home(){
        return "";
    }
}
