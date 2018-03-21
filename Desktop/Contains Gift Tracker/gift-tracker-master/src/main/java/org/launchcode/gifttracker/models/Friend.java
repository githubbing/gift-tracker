package org.launchcode.gifttracker.models;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotEmpty;
import org.launchcode.gifttracker.models.forms.AddFriendForm;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Friend {

    @Id
    @GeneratedValue
    @Column(name = "friend_id")
    private int id;

    @NotEmpty(message = "You must enter a first name.")
    private String firstName;

    @NotEmpty(message = "You must enter a last name.")
    private String lastName;

    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String relationship;

    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<GiftGiven> giftsGiven = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<GiftReceived> giftsReceived = new ArrayList<>();

    //constructor
    public Friend(){}

    public Friend(User user, AddFriendForm addFriendForm){
        setUser(user);
        setAddress(addFriendForm.getAddress());
        setBirthday(addFriendForm.getBirthday());
        setFirstName(addFriendForm.getFirstName());
        setLastName(addFriendForm.getLastName());
        setRelationship(addFriendForm.getRelationship());
        setEmail(addFriendForm.getEmail());
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<GiftGiven> getGiftsGiven() {
        return giftsGiven;
    }

    public void setGiftsGiven(List<GiftGiven> giftsGiven) {
        this.giftsGiven = giftsGiven;
    }

    public List<GiftReceived> getGiftsReceived() {
        return giftsReceived;
    }


    public void addGiftGiven(GiftGiven giftGiven){
        giftsGiven.add(giftGiven);
    }

    public void addGiftReceived(GiftReceived giftReceived){
        giftsReceived.add(giftReceived);
    }

    public void setGiftsReceived(List<GiftReceived> giftsReceived) {
        this.giftsReceived = giftsReceived;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
