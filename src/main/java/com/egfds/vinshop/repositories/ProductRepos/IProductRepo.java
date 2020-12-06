package com.egfds.vinshop.repositories.ProductRepos;

import com.egfds.vinshop.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepo extends CrudRepository<Product, Long> {
}
