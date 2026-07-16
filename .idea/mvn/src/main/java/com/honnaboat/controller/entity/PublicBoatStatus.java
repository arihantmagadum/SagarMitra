package com.HonnaBot.SagaraMitra.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "public_boat_status")
@Data
public class PublicBoatStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;

    @ManyToOne
    @JoinColumn(name = "boat_id", nullable = false)
    private PublicBoat boat;

    @ManyToOne
    @JoinColumn(name = "slot_id", nullable = false)
    private PublicBoatSlot slot;

    private LocalDate bookingDate;
    private int availableSeats;
    private boolean available;             // true = bookable
    private String boatStatus;             // "Available", "Full"
}
