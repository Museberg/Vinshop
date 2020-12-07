package com.egfds.vinshop.services.ProductJPA;

import com.egfds.vinshop.models.Product;
import com.egfds.vinshop.models.ProductType;
import com.egfds.vinshop.repositories.ProductRepos.IProductRepo;
import com.egfds.vinshop.repositories.ProductRepos.IProductTypeRepo;
import com.egfds.vinshop.services.ProductService.IProductService;
import com.egfds.vinshop.services.ProductService.IProductTypeService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductTypeJPA implements IProductTypeService {

    private final IProductTypeRepo productTypeRepo;

    public ProductTypeJPA(IProductTypeRepo productTypeRepo) {
        this.productTypeRepo = productTypeRepo;
    }

    @Override
    public Set<ProductType> findAll() {
        Set<ProductType> set = new HashSet<>();
        productTypeRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public ProductType save(ProductType object) {
        return productTypeRepo.save(object);
    }

    @Override
    public void delete(ProductType object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<ProductType> findById(Long aLong) {
        return productTypeRepo.findById(aLong);
    }
}
