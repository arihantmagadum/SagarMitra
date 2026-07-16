package com.HonnaBot.SagaraMitra.Repositories;

import com.HonnaBot.SagaraMitra.Entity.PrivateBoat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PrivateBoatRepository extends JpaRepository<PrivateBoat, Integer> {

    @Query("SELECT b FROM PrivateBoat b WHERE b.pickupDropLocation = :location")
    List<PrivateBoat> findByPickupDropLocation(String location);

    @Query("SELECT DISTINCT b.pickupDropLocation FROM PrivateBoat b")
    List<String> findDistinctPickupDropLocations();
}
