package com.HonnaBot.SagaraMitra.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "private_boat_slots")
@Data
public class PrivateBoatSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int slotId;

    private String slotTime;
}
