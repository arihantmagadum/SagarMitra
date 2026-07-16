package com.HonnaBot.SagaraMitra.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "public_boat_booking")
@Data
public class PublicBoatBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    /* ---- relations ---- */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "boat_id", nullable = false)
    private PublicBoat boat;

    @ManyToOne
    @JoinColumn(name = "slot_id", nullable = false)
    private PublicBoatSlot slot;

    /* ---- booking details ---- */
    private LocalDate bookingDate;

    private int seatsBooked;

    private String status;    // e.g. "confirmed", "cancelled"
}
