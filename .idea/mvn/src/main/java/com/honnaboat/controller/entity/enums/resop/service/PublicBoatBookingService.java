package com.HonnaBot.SagaraMitra.Service;

import com.HonnaBot.SagaraMitra.Entity.*;
import com.HonnaBot.SagaraMitra.Repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublicBoatBookingService {

    private final PublicBoatRepository boatRepo;
    private final PublicBoatSlotRepository slotRepo;
    private final PublicBoatStatusRepository statusRepo;
    private final PublicBoatBookingRepository bookingRepo;
    private final UserRepository userRepo;

    /* 🔍 helper – free slots on a date */
    public List<PublicBoatSlot> getAvailableSlots(int boatId, LocalDate date) {
        return slotRepo.findAll().stream()
                .filter(slot -> statusRepo
                        .findByBoat_BoatIdAndBookingDateAndSlot_SlotId(boatId, date, slot.getSlotId())
                        .map(PublicBoatStatus::isAvailable)
                        .orElse(false))
                .toList();
    }

    /* 🎫 main booking */
    @Transactional
    public String book(long userId, int boatId, int slotId, LocalDate date, int seats) {

        if (date.isBefore(LocalDate.now())) return "Cannot book for past date.";
        if (seats <= 0) return "Invalid seat count.";

        Optional<User> userOpt = userRepo.findById(userId);
        Optional<PublicBoat> boatOpt = boatRepo.findById(boatId);
        Optional<PublicBoatSlot> slotOpt = slotRepo.findById(slotId);

        if (userOpt.isEmpty() || boatOpt.isEmpty() || slotOpt.isEmpty()) return "Invalid input data.";

        PublicBoatStatus status = statusRepo
                .findByBoat_BoatIdAndBookingDateAndSlot_SlotId(boatId, date, slotId)
                .orElseGet(() -> {
                    PublicBoatStatus newStatus = new PublicBoatStatus();
                    newStatus.setBoat(boatOpt.get());
                    newStatus.setSlot(slotOpt.get());
                    newStatus.setBookingDate(date);
                    newStatus.setAvailableSeats(boatOpt.get().getCapacity());
                    newStatus.setAvailable(true);
                    newStatus.setBoatStatus("Available");
                    return newStatus;
                });

        if (!status.isAvailable()) return "Slot is full.";
        if (status.getAvailableSeats() < seats) return "Only " + status.getAvailableSeats() + " seats available.";

        PublicBoatBooking booking = new PublicBoatBooking();
        booking.setUser(userOpt.get());
        booking.setBoat(boatOpt.get());
        booking.setSlot(slotOpt.get());
        booking.setBookingDate(date);
        booking.setSeatsBooked(seats);
        booking.setStatus("confirmed");
        bookingRepo.save(booking);

        int remainingSeats = status.getAvailableSeats() - seats;
        status.setAvailableSeats(remainingSeats);
        status.setAvailable(remainingSeats > 0);
        status.setBoatStatus(remainingSeats == 0 ? "Full" : "Available");
        statusRepo.save(status);

        return "Public-boat booking successful!";
    }

}
