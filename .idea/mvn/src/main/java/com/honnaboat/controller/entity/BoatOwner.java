package com.HonnaBot.SagaraMitra.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "boat_owners")
@Data
public class BoatOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ownerId;
    private String ownerName;
    private String ownerPhone;
}