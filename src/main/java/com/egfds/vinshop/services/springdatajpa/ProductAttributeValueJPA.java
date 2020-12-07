package com.egfds.vinshop.services.springdatajpa;

import com.egfds.vinshop.models.ProductAttributeValue;
import com.egfds.vinshop.repositories.IProductAttributeValueRepository;
import com.egfds.vinshop.services.Product.IProductAttributeValueService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProductAttributeValueJPA implements IProductAttributeValueService {

    private final IProductAttributeValueRepository productAttributeValueRepository;

    public ProductAttributeValueJPA(IProductAttributeValueRepository productAttributeValueRepository) {
        this.productAttributeValueRepository = productAttributeValueRepository;
    }

    @Override
    public Set<ProductAttributeValue> findAll() {
        return null;
    }

    @Override
    public ProductAttributeValue save(ProductAttributeValue object) {
        return productAttributeValueRepository.save(object);
    }

    @Override
    public void delete(ProductAttributeValue object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<ProductAttributeValue> findById(Long aLong) {
        return Optional.empty();
    }
}
