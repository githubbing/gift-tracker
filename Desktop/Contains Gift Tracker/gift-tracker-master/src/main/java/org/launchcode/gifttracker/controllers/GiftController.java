package org.launchcode.gifttracker.controllers;

import org.launchcode.gifttracker.UserService;
import org.launchcode.gifttracker.models.Friend;
import org.launchcode.gifttracker.models.GiftGiven;
import org.launchcode.gifttracker.models.GiftReceived;
import org.launchcode.gifttracker.models.User;
import org.launchcode.gifttracker.models.data.GiftGivenDao;
import org.launchcode.gifttracker.models.forms.AddGiftGivenForm;
import org.launchcode.gifttracker.models.forms.AddGiftReceivedForm;
import org.launchcode.gifttracker.models.repository.FriendRepository;
import org.launchcode.gifttracker.models.repository.GiftGivenRepository;
import org.launchcode.gifttracker.models.repository.GiftReceivedRepository;
import org.launchcode.gifttracker.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
//@RequestMapping("/gift")
public class GiftController {

    @Autowired
    GiftGivenDao giftGivenDao;

    @Autowired
    private UserService userService;

    @Autowired
    GiftGivenRepository giftGivenRepository;

    @Autowired
    GiftReceivedRepository giftReceivedRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private FriendRepository friendRepository;

    @RequestMapping(value = "addGiftGiven", method = RequestMethod.GET )
    public String displayAddGiftGivenForm(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        AddGiftGivenForm addGiftGivenForm = new AddGiftGivenForm();
        model.addAttribute("user", user);
        model.addAttribute("addGiftGivenForm", addGiftGivenForm);

        if (user.getFriends().isEmpty()){
            return "redirect:/";
        }
        else return "gift/addGiftGiven";
    }

    @RequestMapping(value = "addGiftGiven", method = RequestMethod.POST)
    public String processAddGiftGivenForm(
            @ModelAttribute @Valid AddGiftGivenForm giftGivenForm,
            Errors errors,
            Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        if (errors.hasErrors()) {
            model.addAttribute("addGiftGivenForm", giftGivenForm);
            return "gift/addGiftGiven";
        }



        GiftGiven newGiftGiven = new GiftGiven(
                user,
                giftGivenForm
        );

        Friend friend = giftGivenForm.getFriend();

        System.out.println("fname: " +friend.getFirstName());

        giftGivenRepository.save(newGiftGiven);

        System.out.println("gift given id: " + newGiftGiven.getId());
        user.addGiftGiven(newGiftGiven);
        userRepository.save(user);
        friend.addGiftGiven(newGiftGiven);
        friendRepository.save(friend);
        return "redirect:/";
    }

    @RequestMapping(value = "addGiftReceived", method = RequestMethod.GET )
    public String displayAddGiftReceivedForm(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        AddGiftReceivedForm addGiftReceivedForm = new AddGiftReceivedForm();
        model.addAttribute("user", user);
        model.addAttribute("addGiftReceivedForm", addGiftReceivedForm);

        if (user.getFriends().isEmpty()){
            return "redirect:/";
        }
        else return "gift/addGiftReceived";
    }

    @RequestMapping(value = "addGiftReceived", method = RequestMethod.POST)
    public String processGiftRececeivedForm(
            @ModelAttribute @Valid AddGiftReceivedForm addGiftReceivedForm,
            Errors errors,
            Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        if (errors.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("addGiftReceivedForm", addGiftReceivedForm);
            System.out.println("There's an error");
            return "gift/addGiftReceived";
        }
        GiftReceived newGiftReceived = new GiftReceived(
                user,
                addGiftReceivedForm
        );
        Friend friend = addGiftReceivedForm.getFriend();

        giftReceivedRepository.save(newGiftReceived);

        user.addGiftReceived(newGiftReceived);
        userRepository.save(user);
        friend.addGiftReceived(newGiftReceived);
        friendRepository.save(friend);

        return "redirect:/";
    }

}
