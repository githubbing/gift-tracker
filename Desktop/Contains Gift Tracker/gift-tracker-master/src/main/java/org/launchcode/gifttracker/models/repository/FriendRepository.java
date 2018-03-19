package org.launchcode.gifttracker.models.repository;

import org.launchcode.gifttracker.models.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("friendRepository")
public interface FriendRepository extends JpaRepository<Friend, Integer> {
}
