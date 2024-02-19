package com.example.wishlists.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.Id;


@Entity
public class WishlistItem {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue
    private Long id;

    private String itemName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    private User user;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}

