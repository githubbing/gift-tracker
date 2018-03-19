package org.launchcode.gifttracker.models.repository;

import org.launchcode.gifttracker.models.GiftReceived;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("giftReceivedRepository")
public interface GiftReceivedRepository extends JpaRepository<GiftReceived, Integer>{
    // GiftReceived findByGiftReceived(String giftReceived); <--- there is no property in GiftReceived that is a String and is named giftReceived
    // Don't worry about adding methods here unless you need them.
}
