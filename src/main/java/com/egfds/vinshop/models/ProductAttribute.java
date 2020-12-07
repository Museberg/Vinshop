package com.egfds.vinshop.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product_attributes")
public class ProductAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @ManyToMany(mappedBy = "productAttributes", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<ProductType> productTypes;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "productAttributeOptions",
            joinColumns = {@JoinColumn(name = "product_attributes_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_attribute_values_id")}
            )
    private Set<ProductAttributeValue> productAttributeValues;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductType> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(Set<ProductType> productTypes) {
        this.productTypes = productTypes;
    }

    public Set<ProductAttributeValue> getProductAttributeValues() {
        return productAttributeValues;
    }

    public void setProductAttributeValues(Set<ProductAttributeValue> productAttributeValues) {
        this.productAttributeValues = productAttributeValues;
    }
}
