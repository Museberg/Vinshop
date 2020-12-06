package com.egfds.vinshop.services.ProductJPA;

import com.egfds.vinshop.models.Product;
import com.egfds.vinshop.repositories.ProductRepos.IProductRepo;
import com.egfds.vinshop.services.ProductService.IProductService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductJPA implements IProductService {

    private final IProductRepo productRepo;

    public ProductJPA(IProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Set<Product> findAll() {
        Set<Product> set = new HashSet<>();
        productRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Product save(Product object) {
        return productRepo.save(object);
    }

    @Override
    public void delete(Product object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return productRepo.findById(aLong);
    }
}
