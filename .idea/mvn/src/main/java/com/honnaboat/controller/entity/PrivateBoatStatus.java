package com.HonnaBot.SagaraMitra.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "private_boat_status")
@Data
public class PrivateBoatStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;

    @ManyToOne
    @JoinColumn(name = "boat_id", nullable = false)
    private PrivateBoat boat;

    @ManyToOne
    @JoinColumn(name = "slot_id", nullable = false)
    private PrivateBoatSlot slot;

    private LocalDate bookingDate;

    private boolean available;              // true = available, false = booked

    private String bookingStatus;           // Optional: "Available", "Booked"
}
