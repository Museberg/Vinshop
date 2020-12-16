package com.egfds.vinshop.models;

import javax.persistence.*;
import java.util.Comparator;

@Entity
public class Stock implements Comparable<Stock>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Product product;

    @Column
    private int stockAmount;

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

    public int getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", product=" + product +
                ", stockAmount=" + stockAmount +
                '}';
    }

    @Override
    public int compareTo(Stock o) {
        return Comparator.comparing(Product::getName)
                .compare(this.product, o.product);
    }
}
