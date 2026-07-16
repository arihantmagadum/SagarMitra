package com.HonnaBot.SagaraMitra.controller;

import com.HonnaBot.SagaraMitra.Entity.*;
import com.HonnaBot.SagaraMitra.Repositories.*;
import com.HonnaBot.SagaraMitra.Service.PrivateBoatBookingService;
import com.HonnaBot.SagaraMitra.Service.PublicBoatBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/book")
@CrossOrigin("*")
@RequiredArgsConstructor
public class BookingController {

    private final PublicBoatBookingService publicService;
    private final PrivateBoatBookingService privateService;

    @PostMapping("/public")
    public String bookPublicBoat(@RequestBody Map<String, Object> payload) {
        long userId = Long.parseLong(payload.get("userId").toString());
        int boatId = Integer.parseInt(payload.get("boatId").toString());
        int slotId = Integer.parseInt(payload.get("slotId").toString());
        LocalDate date = LocalDate.parse(payload.get("bookingDate").toString());
        int seats = Integer.parseInt(payload.get("seats").toString());

        return publicService.book(userId, boatId, slotId, date, seats);
    }

    @PostMapping("/private")
    public String bookPrivateBoat(@RequestBody Map<String, Object> payload) {
        long userId = Long.parseLong(payload.get("userId").toString());
        int boatId = Integer.parseInt(payload.get("boatId").toString());
        int slotId = Integer.parseInt(payload.get("slotId").toString());
        LocalDate date = LocalDate.parse(payload.get("bookingDate").toString());

        return privateService.book(userId, boatId, slotId, date);
    }
}
