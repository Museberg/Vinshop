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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zipCode")
    private Set<Address> address;
}
