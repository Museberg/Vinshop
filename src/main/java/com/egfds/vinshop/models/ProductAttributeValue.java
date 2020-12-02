package com.egfds.vinshop.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product_attribute_values")
public class ProductAttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String value;
    @ManyToMany
    private Set<ProductAttribute> productAttributes;
}
