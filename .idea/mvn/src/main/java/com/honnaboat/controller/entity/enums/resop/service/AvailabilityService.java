package com.HonnaBot.SagaraMitra.Service;

import com.HonnaBot.SagaraMitra.Entity.PrivateBoatStatus;
import com.HonnaBot.SagaraMitra.Repositories.PrivateBoatStatusRepository;
import com.HonnaBot.SagaraMitra.Repositories.PublicBoatStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AvailabilityService {

    private final PublicBoatStatusRepository pubStatusRepo;
    private final PrivateBoatStatusRepository priStatusRepo;

    public boolean isPublicAvailable(int boatId, LocalDate date, int slotId, int seats) {
        return pubStatusRepo.findByBoat_BoatIdAndBookingDateAndSlot_SlotId(boatId, date, slotId)
                .filter(s -> s.isAvailable() && s.getAvailableSeats() >= seats)
                .isPresent();
    }

    public boolean isPrivateAvailable(int boatId, LocalDate date, int slotId) {
        return priStatusRepo.findByBoat_BoatIdAndBookingDateAndSlot_SlotId(boatId, date, slotId)
                .map(PrivateBoatStatus::isAvailable)
                .orElse(false);
    }
}
