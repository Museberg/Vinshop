package com.egfds.vinshop.repositories.ProductRepos;

import com.egfds.vinshop.models.ProductType;
import org.springframework.data.repository.CrudRepository;

public interface IProductTypeRepo extends CrudRepository<ProductType, Long> {
}
