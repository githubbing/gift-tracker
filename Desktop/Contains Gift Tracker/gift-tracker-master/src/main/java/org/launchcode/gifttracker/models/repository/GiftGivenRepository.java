package org.launchcode.gifttracker.models.repository;

import org.launchcode.gifttracker.models.GiftGiven;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("giftGivenRepository")
public interface GiftGivenRepository extends JpaRepository<GiftGiven, Integer> {
}
