package com.HonnaBot.SagaraMitra.controller;

import com.HonnaBot.SagaraMitra.Entity.BoatOwner;
import com.HonnaBot.SagaraMitra.Entity.PrivateBoat;
import com.HonnaBot.SagaraMitra.Entity.PublicBoat;
import com.HonnaBot.SagaraMitra.Repositories.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BoatService boatService;

    @PostMapping("/addPublicBoat")
    public PublicBoat addPublicBoat(@RequestBody PublicBoat boat) {
        return boatService.addPublicBoat(boat);
    }

    @PostMapping("/addPrivateBoat")
    public PrivateBoat addPrivateBoat(@RequestBody PrivateBoat boat) {
        return boatService.addPrivateBoat(boat);
    }

    @PostMapping("/addBoatOwner")
    public BoatOwner addBoatOwner(@RequestBody BoatOwner owner) {
        return boatService.addBoatOwner(owner);
    }



    @PutMapping("/assignOwner/{boatId}/{ownerId}")
    public PublicBoat assignOwnerToPublicBoat(@PathVariable int boatId, @PathVariable int ownerId) {
        return boatService.assignOwnerToPublicBoat(boatId, ownerId);
    }

    @GetMapping("/publicBoats")
    public List<PublicBoat> getAllPublicBoats() {
        return boatService.getAllPublicBoats();
    }

    @GetMapping("/privateBoats")
    public List<PrivateBoat> getAllPrivateBoats() {
        return boatService.getAllPrivateBoats();
    }
    @GetMapping("/boatOwners")
    public List<BoatOwner> getAllBoatOwners() {
        return boatService.getAllBoatOwners();
    }

    @DeleteMapping("/deleteBoat/{boatId}")
    public ResponseEntity<String> deleteBoat(@PathVariable int boatId) {
        boatService.deleteBoat(boatId);
        return ResponseEntity.ok("Boat deleted successfully!");
    }


}
