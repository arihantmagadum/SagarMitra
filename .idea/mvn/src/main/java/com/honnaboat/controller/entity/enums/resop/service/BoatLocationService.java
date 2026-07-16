package com.HonnaBot.SagaraMitra.Service;

import com.HonnaBot.SagaraMitra.Repositories.PrivateBoatRepository;
import com.HonnaBot.SagaraMitra.Repositories.PublicBoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BoatLocationService {

    @Autowired
    private PublicBoatRepository publicBoatRepository;

    @Autowired
    private PrivateBoatRepository privateBoatRepository;

    public Set<String> getAllDistinctLocations() {
        Set<String> locations = new HashSet<>();
        locations.addAll(publicBoatRepository.findDistinctPickupDropLocations());
        locations.addAll(privateBoatRepository.findDistinctPickupDropLocations());
        return locations;
    }
}

