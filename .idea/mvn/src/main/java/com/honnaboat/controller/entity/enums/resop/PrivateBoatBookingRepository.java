package com.HonnaBot.SagaraMitra.Repositories;

import com.HonnaBot.SagaraMitra.Entity.PrivateBoatBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrivateBoatBookingRepository extends JpaRepository<PrivateBoatBooking, Integer> {

    List<PrivateBoatBooking> findByBoat_BoatIdAndBookingDate(int boatId, LocalDate bookingDate);

    List<PrivateBoatBooking> findByBoat_BoatIdAndBookingDateAndSlot_SlotId(int boatId,
                                                                           LocalDate bookingDate,
                                                                           int slotId);

    boolean existsByBoat_BoatIdAndBookingDateAndSlot_SlotId(int boatId, LocalDate date, int slotId); // ✅ only this
}
