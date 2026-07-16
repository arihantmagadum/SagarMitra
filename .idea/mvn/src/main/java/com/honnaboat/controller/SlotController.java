package com.HonnaBot.SagaraMitra.controller;

import com.HonnaBot.SagaraMitra.Entity.PrivateBoatSlot;
import com.HonnaBot.SagaraMitra.Entity.PublicBoatSlot;
import com.HonnaBot.SagaraMitra.Repositories.PrivateBoatSlotRepository;
import com.HonnaBot.SagaraMitra.Repositories.PublicBoatSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/slots")
@CrossOrigin("*")
public class SlotController {

    @Autowired
    private PublicBoatSlotRepository publicSlotRepo;
    @Autowired private PrivateBoatSlotRepository privateSlotRepo;

    @GetMapping("/public-slots")
    public List<PublicBoatSlot> getPublicSlots() {
        return publicSlotRepo.findAll();
    }

    @GetMapping("/private-slots")
    public List<PrivateBoatSlot> getPrivateSlots() {
        return privateSlotRepo.findAll();
    }
}
