package com.egfds.vinshop.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product_types")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @ManyToMany(mappedBy = "productTypes")
    private Set<ProductAttribute> productAttributes;
}
