package org.launchcode.gifttracker.models.forms;

import org.hibernate.validator.constraints.NotEmpty;
import org.launchcode.gifttracker.models.Friend;
import org.launchcode.gifttracker.models.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class AddGiftGivenForm {


    //variables
    @NotEmpty(message = "You must describe the gift!")
    private String description;

    @NotEmpty(message = "You must enter a price!")
    private String price;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateGiven;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@NotNull(message = "You must enter the date bought!")
    private Date dateBought;

    private String location;

    private Friend friend;

    private User user;

    //constructor
    public AddGiftGivenForm() {
    }

    public AddGiftGivenForm(String description, String price, Date dateBought, Date dateGiven, Friend friend, User user){

    }

    //getters and setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getDateGiven() {
        return dateGiven;
    }

    public void setDateGiven(Date dateGiven) {
        this.dateGiven = dateGiven;
    }
    public Date getDateBought() {
        return dateBought;
    }

    public void setDateBought(Date dateBought) {
        this.dateBought = dateBought;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
