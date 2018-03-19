package org.launchcode.gifttracker.models.data;

import org.launchcode.gifttracker.models.GiftReceived;
import org.springframework.data.repository.CrudRepository;

public interface GiftReceivedDao extends CrudRepository<GiftReceived, Integer> {
}
