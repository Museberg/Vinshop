package com.egfds.vinshop.models;

import javax.persistence.*;

@Entity
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String label;

    // Each attribute contains a reference to ONE (1) product type
    @ManyToOne
    private ProductType type;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", type=" + type +
                '}';
    }
}
