package com.egfds.vinshop.models;

import javax.persistence.*;

@Entity
public class EmailNewsletter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String email;

    public EmailNewsletter() {
    }

    public EmailNewsletter(long id, String email) {
        this.id = id;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailForNewsletter{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }

}
