package com.egfds.vinshop.models;

import javax.persistence.*;

@Entity
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String label;

    // Each attribute contains a reference to ONE (1) entity type
    @ManyToOne
    private EntityType type;



}
