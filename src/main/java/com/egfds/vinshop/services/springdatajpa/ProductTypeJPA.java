package com.egfds.vinshop.services.springdatajpa;

import com.egfds.vinshop.models.Product;
import com.egfds.vinshop.models.ProductType;
import com.egfds.vinshop.repositories.IProductTypeRepository;
import com.egfds.vinshop.services.Product.IProductService;
import com.egfds.vinshop.services.Product.IProductTypeService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProductTypeJPA implements IProductTypeService {

    private final IProductTypeRepository productTypeRepository;

    public ProductTypeJPA(IProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public Set<ProductType> findAll() {
        return null;
    }

    @Override
    public ProductType save(ProductType object) {
        return productTypeRepository.save(object);
    }

    @Override
    public void delete(ProductType object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<ProductType> findById(Long aLong) {
        return productTypeRepository.findById(aLong);
    }
}
