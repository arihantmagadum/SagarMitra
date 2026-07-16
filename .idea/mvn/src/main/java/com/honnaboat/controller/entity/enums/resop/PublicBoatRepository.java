package com.HonnaBot.SagaraMitra.Repositories;

import com.HonnaBot.SagaraMitra.Entity.PublicBoat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PublicBoatRepository extends JpaRepository<PublicBoat, Integer> {

    @Query("SELECT b FROM PublicBoat b WHERE b.pickupDropLocation = :location")
    List<PublicBoat> findByPickupDropLocation(String location);

    @Query("SELECT DISTINCT b.pickupDropLocation FROM PublicBoat b")
    List<String> findDistinctPickupDropLocations();
}
