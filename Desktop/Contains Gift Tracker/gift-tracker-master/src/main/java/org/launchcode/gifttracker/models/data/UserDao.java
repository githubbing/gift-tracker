package org.launchcode.gifttracker.models.data;

import org.launchcode.gifttracker.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Integer> {
}
