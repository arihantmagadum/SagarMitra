package com.HonnaBot.SagaraMitra.Repositories;

import com.HonnaBot.SagaraMitra.Entity.PublicBoatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PublicBoatStatusRepository extends JpaRepository<PublicBoatStatus, Integer> {

    /* Find by boat ID, date, and slot ID */
    Optional<PublicBoatStatus> findByBoat_BoatIdAndBookingDateAndSlot_SlotId(int boatId,
                                                                             LocalDate bookingDate,
                                                                             int slotId);
}
