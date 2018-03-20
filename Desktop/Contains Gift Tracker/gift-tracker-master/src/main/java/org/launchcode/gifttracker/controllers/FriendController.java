package org.launchcode.gifttracker.controllers;


import org.launchcode.gifttracker.UserService;
import org.launchcode.gifttracker.models.Friend;
import org.launchcode.gifttracker.models.GiftGiven;
import org.launchcode.gifttracker.models.User;
import org.launchcode.gifttracker.models.data.FriendDao;
import org.launchcode.gifttracker.models.forms.AddFriendForm;
import org.launchcode.gifttracker.models.repository.FriendRepository;
import org.launchcode.gifttracker.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Console;
import java.net.Authenticator;
import java.util.Collection;

@Controller
@RequestMapping("/")
public class FriendController {

    @Autowired
    FriendDao friendDao;

    @Autowired
    FriendRepository friendRepository;

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;


    @RequestMapping(value = "addFriend", method = RequestMethod.GET)
    public String displayFriendForm(Model model) {
        AddFriendForm addFriendForm = new AddFriendForm();
        model.addAttribute("addFriendForm", addFriendForm);
        return "friend/addFriend";
    }

    @RequestMapping(value = "addFriend", method = RequestMethod.POST)
    public String processFriendForm(@ModelAttribute @Valid AddFriendForm addFriendForm,
                                    Errors errors, Model model, AddFriendForm friendForm) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        if (errors.hasErrors()){
            model.addAttribute("addFriendForm", friendForm);
            return "friend/addFriend";
        }
        Friend newFriend = new Friend(user, friendForm);
        friendRepository.save(newFriend);
        user.addFriend(newFriend);
        userRepository.save(user);

        return "redirect:/";
    }

    @RequestMapping(path = "friend/view/{friendId}", method = RequestMethod.GET)
    public String viewFriend(@ModelAttribute
                             @PathVariable int friendId,
                             Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Friend viewFriend = friendRepository.findOne(friendId);

        model.addAttribute("user", user);
        model.addAttribute("friend", viewFriend);
        model.addAttribute("title", viewFriend.getLastName());

        System.out.println(viewFriend.getGiftsReceived());
        System.out.println(viewFriend.getGiftsGiven());

      /*  if (user != null && user.getId() == friendId){
            model.addAttribute("editFriend", friendId);
        }*/
        return "friend/viewGifts";
    }
}
