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
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "ProductType_Attributes",
            joinColumns = {@JoinColumn(name = "product_types_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_attribute_id")}
    )
    private Set<ProductAttribute> productAttributes;

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

    public Set<ProductAttribute> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(Set<ProductAttribute> productAttributes) {
        this.productAttributes = productAttributes;
    }
}
