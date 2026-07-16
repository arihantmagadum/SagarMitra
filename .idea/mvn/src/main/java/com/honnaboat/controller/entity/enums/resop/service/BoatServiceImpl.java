package com.HonnaBot.SagaraMitra.Service;

import com.HonnaBot.SagaraMitra.Entity.BoatOwner;
import com.HonnaBot.SagaraMitra.Entity.PrivateBoat;
import com.HonnaBot.SagaraMitra.Entity.PublicBoat;
import com.HonnaBot.SagaraMitra.Repositories.BoatOwnerRepository;
import com.HonnaBot.SagaraMitra.Repositories.BoatService;
import com.HonnaBot.SagaraMitra.Repositories.PrivateBoatRepository;
import com.HonnaBot.SagaraMitra.Repositories.PublicBoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoatServiceImpl implements BoatService {

    @Autowired
    private PublicBoatRepository publicBoatRepository;

    @Autowired
    private PrivateBoatRepository privateBoatRepository;

    @Autowired
    private BoatOwnerRepository boatOwnerRepository;

    @Override
    public PublicBoat addPublicBoat(PublicBoat boat) {
        return publicBoatRepository.save(boat);
    }

    @Override
    public PrivateBoat addPrivateBoat(PrivateBoat boat) {
        return privateBoatRepository.save(boat);
    }

    @Override
    public BoatOwner addBoatOwner(BoatOwner owner) {
        return boatOwnerRepository.save(owner);
    }

    @Override
    public PublicBoat assignOwnerToPublicBoat(int boatId, int ownerId) {
        Optional<PublicBoat> boatOpt = publicBoatRepository.findById(boatId);
        Optional<BoatOwner> ownerOpt = boatOwnerRepository.findById(ownerId);

        if (boatOpt.isPresent() && ownerOpt.isPresent()) {
            PublicBoat boat = boatOpt.get();
            boat.setOwner(ownerOpt.get());
            return publicBoatRepository.save(boat);
        }
        throw new RuntimeException("Boat or Owner not found");
    }

    @Override
    public List<PublicBoat> getAllPublicBoats() {
        return publicBoatRepository.findAll();
    }

    @Override
    public List<PrivateBoat> getAllPrivateBoats() {
        return privateBoatRepository.findAll();
    }
    @Override
    public List<BoatOwner> getAllBoatOwners() {
        return boatOwnerRepository.findAll();
    }

    @Override
    public void deleteBoat(int boatId) {
        publicBoatRepository.deleteById(boatId);
        privateBoatRepository.deleteById(boatId);
    }


}