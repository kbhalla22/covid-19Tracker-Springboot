package io.karrybee.covid19Tracker.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller//Render an html ui
public class MainController {

@GetMapping("/")
    public String home(Model model){
    model.addAttribute("testName","TEST");
        return "home";
    }
}
