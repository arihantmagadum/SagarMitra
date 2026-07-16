package com.HonnaBot.SagaraMitra.Repositories;

import com.HonnaBot.SagaraMitra.Entity.PublicBoatBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PublicBoatBookingRepository extends JpaRepository<PublicBoatBooking, Integer> {

    List<PublicBoatBooking> findByBoat_BoatIdAndBookingDate(int boatId, LocalDate bookingDate);

    List<PublicBoatBooking> findByBoat_BoatIdAndBookingDateAndSlot_SlotId(int boatId,
                                                                          LocalDate bookingDate,
                                                                          int slotId);

    @Query("SELECT COALESCE(SUM(b.seatsBooked), 0) FROM PublicBoatBooking b " +
            "WHERE b.boat.boatId = :boatId AND b.bookingDate = :date AND b.slot.slotId = :slotId")
    int getSeatsAlreadyBooked(@Param("boatId") int boatId,
                              @Param("date") LocalDate date,
                              @Param("slotId") int slotId);
}
