package com.HonnaBot.SagaraMitra.controller;

import com.HonnaBot.SagaraMitra.Service.BoatLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/locations")
public class BoatLocationController {

    @Autowired
    private BoatLocationService boatLocationService;

    @GetMapping("/distinct")
    public Set<String> getDistinctLocations() {
        return boatLocationService.getAllDistinctLocations();
    }
}
