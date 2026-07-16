package com.HonnaBot.SagaraMitra.Repositories;

import com.HonnaBot.SagaraMitra.Entity.PrivateBoatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PrivateBoatStatusRepository extends JpaRepository<PrivateBoatStatus, Integer> {

    Optional<PrivateBoatStatus> findByBoat_BoatIdAndBookingDateAndSlot_SlotId(int boatId,
                                                                              LocalDate bookingDate,
                                                                              int slotId);
}
