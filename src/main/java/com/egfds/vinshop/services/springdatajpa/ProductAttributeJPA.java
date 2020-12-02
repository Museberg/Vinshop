package com.egfds.vinshop.services.springdatajpa;

import com.egfds.vinshop.models.ProductAttribute;
import com.egfds.vinshop.repositories.IProductAttributeRepository;
import com.egfds.vinshop.services.Product.IProductAttributeService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductAttributeJPA implements IProductAttributeService {

    private final IProductAttributeRepository productAttributeRepository;

    public ProductAttributeJPA(IProductAttributeRepository productAttributeRepository) {
        this.productAttributeRepository = productAttributeRepository;
    }

    @Override
    public Set<ProductAttribute> findAll() {
        Set<ProductAttribute> productAttributes = new HashSet<>();
        productAttributeRepository.findAll().forEach(productAttributes::add);

        return productAttributes;
    }

    @Override
    public ProductAttribute save(ProductAttribute object) {
        return null;
    }

    @Override
    public void delete(ProductAttribute object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<ProductAttribute> findById(Long aLong) {
        return Optional.empty();
    }
}
