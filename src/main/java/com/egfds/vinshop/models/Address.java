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
    private Zip zip;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<User> users;

    public Address(long id, Set<User> users) {
        this.id = id;
        this.users = users;
    }

    public Address() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Zip getZip() {
        return zip;
    }

    public void setZip(Zip zipCode) {
        this.zip = zipCode;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", zipCode=" + zip +
                ", users=" + users +
                '}';
    }
}
