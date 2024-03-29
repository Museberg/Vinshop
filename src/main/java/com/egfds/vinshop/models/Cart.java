package com.egfds.vinshop.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User user;

    @OneToMany
    private List<CartItem> items;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public int getCartLength() {
        int length = 0;
        for (CartItem item : items) {
            length += item.getAmount();
        }
        return length;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                ", items=" + items +
                '}';
    }

    public Cart() {
    }

    public Cart(long id, User user, List<CartItem> items) {
        this.id = id;
        this.user = user;
        this.items = items;
    }

    public Cart(List<CartItem> items) {
        this.items = items;
    }
}
