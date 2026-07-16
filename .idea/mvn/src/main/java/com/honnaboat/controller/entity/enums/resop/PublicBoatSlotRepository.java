package com.HonnaBot.SagaraMitra.Repositories;

import com.HonnaBot.SagaraMitra.Entity.PublicBoatSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicBoatSlotRepository extends JpaRepository<PublicBoatSlot, Integer> {
    // JpaRepository already provides findAll(), findById(), etc.
}
