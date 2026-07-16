package com.HonnaBot.SagaraMitra.Service;

import com.HonnaBot.SagaraMitra.Entity.*;
import com.HonnaBot.SagaraMitra.Repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrivateBoatBookingService {

    private final PrivateBoatRepository boatRepo;
    private final PrivateBoatSlotRepository slotRepo;
    private final PrivateBoatStatusRepository statusRepo;
    private final PrivateBoatBookingRepository bookingRepo;
    private final UserRepository userRepo;

    @Transactional
    public String book(long userId, int boatId, int slotId, LocalDate date) {

        if (date.isBefore(LocalDate.now())) return "Past dates not allowed.";

        Optional<User> userOpt = userRepo.findById(userId);
        Optional<PrivateBoat> boatOpt = boatRepo.findById(boatId);
        Optional<PrivateBoatSlot> slotOpt = slotRepo.findById(slotId);

        if (userOpt.isEmpty() || boatOpt.isEmpty() || slotOpt.isEmpty()) return "Invalid user/boat/slot.";

        boolean alreadyBooked = bookingRepo.existsByBoat_BoatIdAndBookingDateAndSlot_SlotId(boatId, date, slotId);
        if (alreadyBooked) return "Slot is already booked.";

        // Create booking
        PrivateBoatBooking booking = new PrivateBoatBooking();
        booking.setUser(userOpt.get());
        booking.setBoat(boatOpt.get());
        booking.setSlot(slotOpt.get());
        booking.setBookingDate(date);
        booking.setStatus("confirmed");
        bookingRepo.save(booking);

        // Update or insert status
        PrivateBoatStatus status = statusRepo.findByBoat_BoatIdAndBookingDateAndSlot_SlotId(boatId, date, slotId)
                .orElse(new PrivateBoatStatus());

        status.setBoat(boatOpt.get());
        status.setSlot(slotOpt.get());
        status.setBookingDate(date);
        status.setAvailable(false);
        status.setBookingStatus("Booked");
        statusRepo.save(status);

        return "Private-boat booking confirmed!";
    }

}
