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

    @RequestMapping(value = "friend/view/(friendId}", method = RequestMethod.GET)
    public String viewGiftByFriend(@ModelAttribute @Valid Errors errors, Model model, @PathVariable int friendId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        Friend friend = friendRepository.findOne(friendId);
        if(friend.getUser().getId() == friendId ){
            friend.getGiftsGiven();
            friend.getGiftsReceived();
            model.addAttribute(friend);
            return "redirect:friend/view/" + friendId;
        }
        else return "redirect:home";
    }


}
