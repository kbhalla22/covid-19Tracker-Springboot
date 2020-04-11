package io.karrybee.covid19Tracker.controllers;


import io.karrybee.covid19Tracker.models.LocationStats;
import io.karrybee.covid19Tracker.services.Covid19DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller//Render an html ui
public class MainController {
@Autowired
    Covid19DataService covid19DataService;
@GetMapping("/")
    public String home(Model model){
    List<LocationStats>allStats=covid19DataService.getAllStats();
    /*
    Taking a list of objects, converting it into a stream, each object maps to integer value,
    take stream and sum it up.
     */
    int totalReportedCases= allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
    int totalNewCases= allStats.stream().mapToInt(stat1->stat1.getDiffFromPrevDay()).sum();

    model.addAttribute("locationStats",allStats);
    model.addAttribute("totalReportedCases",totalReportedCases);
    model.addAttribute("totalNewCases",totalNewCases);

        return "home";
    }
}
