package org.launchcode.gifttracker;


import org.launchcode.gifttracker.models.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}