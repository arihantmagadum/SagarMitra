package com.HonnaBot.SagaraMitra.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "public_boat_slots")
@Data
public class PublicBoatSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int slotId;

    private String slotTime;
}
