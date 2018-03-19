package org.launchcode.gifttracker.models.forms;

import org.hibernate.validator.constraints.NotEmpty;
import org.launchcode.gifttracker.models.Friend;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.zip.DataFormatException;

public class AddGiftReceivedForm {

    @NotEmpty(message = "You must enter what you received.")
    private String description;

    //@NotNull(message = "You must enter when you received the gift.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateReceived;

    @NotEmpty(message = "You must enter how much you think the gift cost.")
    private String price;

    @NotNull(message = "You must enter who gave you the gift.")
    private Friend friend;

    public AddGiftReceivedForm(){}
    //constructor
    public AddGiftReceivedForm(String description, Date dateReceived, String price, Friend friend) {
    }


    //getters and setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }
}
