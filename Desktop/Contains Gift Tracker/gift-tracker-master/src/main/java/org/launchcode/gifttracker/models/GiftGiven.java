package org.launchcode.gifttracker.models;

import org.launchcode.gifttracker.models.forms.AddGiftGivenForm;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class GiftGiven {


    @Id
    @GeneratedValue
    @Column(name = "giftGiven_id")
    private int id;

    @NotNull(message = "You must describe the gift.")
    private String description;

    @NotNull(message = "You must enter a price!")
    private String price;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateGiven;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateBought;

    private String location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    private Friend friend;

    //constructor
    public GiftGiven(){}

    public GiftGiven(User user, AddGiftGivenForm addGiftGivenForm){
        setUser(user);
        setDateBought(addGiftGivenForm.getDateBought());
        setDateGiven(addGiftGivenForm.getDateGiven());
        setDescription(addGiftGivenForm.getDescription());
        setLocation(addGiftGivenForm.getLocation());
        setPrice(addGiftGivenForm.getPrice());
        setFriend(addGiftGivenForm.getFriend());
    }

    //getters and setters
    public int getId() {
        return id;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }
}
