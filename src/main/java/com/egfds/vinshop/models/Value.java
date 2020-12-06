package com.egfds.vinshop.models;

import javax.persistence.*;

@Entity
public class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Each value contains one reference to ONE (1) product
    @ManyToOne
    private Product product;

    // Each value contains one reference to ONE (1) product type (red wine, cheese, etc.)
    @ManyToOne
    private ProductType type;

    // Each value contains one reference to ONE (1) attribute
    @ManyToOne
    private Attribute attribute;

    @Column
    private String value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", product=" + product +
                ", type=" + type +
                ", attribute=" + attribute +
                ", value='" + value + '\'' +
                '}';
    }
}
