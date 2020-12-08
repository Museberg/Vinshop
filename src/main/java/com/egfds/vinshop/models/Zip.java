package com.egfds.vinshop.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "zipcodes")
public class Zip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String zipCode;

    @Column
    private String city;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zipCode")
    private Set<Address> address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Zip{" +
                "id=" + id +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", address=" + address +
                '}';
    }
}
