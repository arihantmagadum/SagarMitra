package com.HonnaBot.SagaraMitra.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "public_boats")
@Data
public class PublicBoat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boatId;
    private String boatName;
    private int capacity;
    private String pickupDropLocation;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private BoatOwner owner;
}