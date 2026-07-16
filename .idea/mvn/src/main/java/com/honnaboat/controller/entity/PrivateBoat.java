package com.HonnaBot.SagaraMitra.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "private_boats")
@Data
public class PrivateBoat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boatId;
    private String boatName;
    private String pickupDropLocation;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private BoatOwner owner;
}
