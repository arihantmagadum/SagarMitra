package com.HonnaBot.SagaraMitra.controller;

import com.HonnaBot.SagaraMitra.Entity.PrivateBoat;
import com.HonnaBot.SagaraMitra.Entity.PublicBoat;
import com.HonnaBot.SagaraMitra.Repositories.PrivateBoatRepository;
import com.HonnaBot.SagaraMitra.Repositories.PublicBoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/boats")
public class BoatController {

    @Autowired
    private PublicBoatRepository publicBoatRepo;

    @Autowired
    private PrivateBoatRepository privateBoatRepo;

    @GetMapping("/by-location")
    public Map<String, Object> getBoatsByLocation(@RequestParam String location) {
        System.out.println("Looking for boats at: " + location);
        List<PublicBoat> publicBoats = publicBoatRepo.findByPickupDropLocation(location);
        List<PrivateBoat> privateBoats = privateBoatRepo.findByPickupDropLocation(location);

        Map<String, Object> response = new HashMap<>();
        response.put("publicBoats", publicBoats);
        response.put("privateBoats", privateBoats);

        return response;
    }
}

