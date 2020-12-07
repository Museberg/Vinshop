package com.egfds.vinshop.services.springdatajpa;

import com.egfds.vinshop.models.Product;
import com.egfds.vinshop.services.Product.IProductService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProductJPA implements IProductService {
    @Override
    public Set<Product> findAll() {
        return null;
    }

    @Override
    public Product save(Product object) {
        return null;
    }

    @Override
    public void delete(Product object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return Optional.empty();
    }
}
