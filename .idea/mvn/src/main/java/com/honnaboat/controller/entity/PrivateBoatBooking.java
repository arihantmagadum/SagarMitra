package com.HonnaBot.SagaraMitra.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "private_boat_booking")
@Data
public class PrivateBoatBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "boat_id", nullable = false)
    private PrivateBoat boat;

    @ManyToOne
    @JoinColumn(name = "slot_id", nullable = false)
    private PrivateBoatSlot slot;

    private LocalDate bookingDate;

    private String status;    // "confirmed", "cancelled"
}
