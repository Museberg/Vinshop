package com.egfds.vinshop.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product_attribute")
public class ProductAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @ManyToMany
    private Set<ProductType> productTypes;
    @ManyToMany(mappedBy = "productAttributes")
    private Set<ProductAttributeValue> productAttributeValues;
}
