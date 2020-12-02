package com.egfds.vinshop.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String street;
    @Column
    private String streetNumber;

    @ManyToOne
    @JoinColumn(name = "zipCode_id")
    private Zip zipCode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
    private Set<User> users;

    public Address(long id, Set<User> users) {
        this.id = id;
        this.users = users;
    }

    public Address() {
    }
}
