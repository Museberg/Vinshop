package com.egfds.vinshop.repositories;

import com.egfds.vinshop.models.Address;
import com.egfds.vinshop.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<Product, Long> {

}
