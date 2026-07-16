package com.HonnaBot.SagaraMitra.Repositories;

import com.HonnaBot.SagaraMitra.Entity.BoatOwner;
import com.HonnaBot.SagaraMitra.Entity.PrivateBoat;
import com.HonnaBot.SagaraMitra.Entity.PublicBoat;

import java.util.List;

public interface BoatService {
    PublicBoat addPublicBoat(PublicBoat boat);
    PrivateBoat addPrivateBoat(PrivateBoat boat);
    BoatOwner addBoatOwner(BoatOwner owner);
    PublicBoat assignOwnerToPublicBoat(int boatId, int ownerId);
    List<PublicBoat> getAllPublicBoats();
    List<PrivateBoat> getAllPrivateBoats();

    List<BoatOwner> getAllBoatOwners();
    void deleteBoat(int boatId);

}
