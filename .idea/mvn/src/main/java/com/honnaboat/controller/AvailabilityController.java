package com.HonnaBot.SagaraMitra.controller;

import com.HonnaBot.SagaraMitra.Entity.PublicBoatStatus;
import com.HonnaBot.SagaraMitra.Entity.PrivateBoatStatus;
import com.HonnaBot.SagaraMitra.Repositories.PublicBoatStatusRepository;
import com.HonnaBot.SagaraMitra.Repositories.PrivateBoatStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/availability")
@CrossOrigin("*")
public class AvailabilityController {

    @Autowired
    private PublicBoatStatusRepository publicStatusRepo;

    @Autowired
    private PrivateBoatStatusRepository privateStatusRepo;

    /** ✅ PUBLIC BOAT AVAILABILITY CHECK */
    @GetMapping("/public")
    public Map<String, Object> checkPublicBoat(@RequestParam int boatId,
                                               @RequestParam String date,
                                               @RequestParam int slotId,
                                               @RequestParam int seats) {
        Map<String, Object> response = new HashMap<>();

        // 1. Past date check
        LocalDate bookingDate = LocalDate.parse(date);
        if (bookingDate.isBefore(LocalDate.now())) {
            response.put("available", false);
            response.put("message", "Cannot book for past dates.");
            return response;
        }

        // 2. Fetch availability status
        Optional<PublicBoatStatus> statusOpt =
                publicStatusRepo.findByBoat_BoatIdAndBookingDateAndSlot_SlotId(
                        boatId, bookingDate, slotId);

        if (statusOpt.isEmpty()) {
            response.put("available", false);
            response.put("message", "Slot data not available for this date.");
            return response;
        }

        PublicBoatStatus status = statusOpt.get();
        int seatsLeft = status.getAvailableSeats();
        boolean ok = status.isAvailable() && seatsLeft >= seats;

        response.put("available", ok);
        response.put("seatsLeft", seatsLeft);
        response.put("message",
                ok ? "Slot available" : (seatsLeft == 0 ? "Slot is full." : "Only " + seatsLeft + " seats left."));
        return response;
    }

    /** ✅ PRIVATE BOAT AVAILABILITY CHECK */
    @GetMapping("/private")
    public Map<String, Object> checkPrivateBoat(@RequestParam int boatId,
                                                @RequestParam String date,
                                                @RequestParam int slotId) {
        Map<String, Object> response = new HashMap<>();

        LocalDate bookingDate = LocalDate.parse(date);
        if (bookingDate.isBefore(LocalDate.now())) {
            response.put("available", false);
            response.put("message", "Cannot book for past dates.");
            return response;
        }

        Optional<PrivateBoatStatus> statusOpt =
                privateStatusRepo.findByBoat_BoatIdAndBookingDateAndSlot_SlotId(
                        boatId, bookingDate, slotId);

        boolean ok = statusOpt.isPresent() && statusOpt.get().isAvailable();
        response.put("available", ok);
        response.put("message", ok ? "Slot available" : "Not available or already booked.");
        return response;
    }
}
