package org.launchcode.gifttracker.models;


import org.hibernate.validator.constraints.NotEmpty;
import org.launchcode.gifttracker.models.forms.AddGiftGivenForm;
import org.launchcode.gifttracker.models.forms.AddGiftReceivedForm;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
public class GiftReceived {


    @Id
    @GeneratedValue
    @Column(name = "giftReceived_id")
    private int id;

    @NotEmpty(message = "You must describe the gift.")
    private String description;

    @NotEmpty(message = "You must enter a price!")
    private String price;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateReceived;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    private Friend friend;

    //constructor
    public GiftReceived(User user, AddGiftReceivedForm addGiftReceivedForm){
        setDescription(addGiftReceivedForm.getDescription());
        setUser(user);
        setDateReceived(addGiftReceivedForm.getDateReceived());
        setFriend(addGiftReceivedForm.getFriend());
        setPrice(addGiftReceivedForm.getPrice());
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

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
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
