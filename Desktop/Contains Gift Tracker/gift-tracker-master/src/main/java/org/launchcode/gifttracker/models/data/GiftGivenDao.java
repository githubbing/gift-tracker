package org.launchcode.gifttracker.models.data;

import org.launchcode.gifttracker.models.GiftGiven;
import org.springframework.data.repository.CrudRepository;

public interface GiftGivenDao extends CrudRepository<GiftGiven, Integer> {
}
