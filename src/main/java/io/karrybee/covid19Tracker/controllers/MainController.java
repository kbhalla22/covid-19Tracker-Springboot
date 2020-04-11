package io.karrybee.covid19Tracker.controllers;


import io.karrybee.covid19Tracker.services.Covid19DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller//Render an html ui
public class MainController {
@Autowired
    Covid19DataService covid19DataService;
@GetMapping("/")
    public String home(Model model){
    model.addAttribute("locationStats",covid19DataService.getAllStats());
        return "home";
    }
}
