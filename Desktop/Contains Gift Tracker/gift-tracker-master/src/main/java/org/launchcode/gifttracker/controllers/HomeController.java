package org.launchcode.gifttracker.controllers;


import org.launchcode.gifttracker.UserService;
import org.launchcode.gifttracker.models.User;
import org.launchcode.gifttracker.models.repository.FriendRepository;
import org.launchcode.gifttracker.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        System.out.println("User: " + user.getEmail());
        model.addAttribute(user);
            return "home";
        }
}

