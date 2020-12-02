package com.egfds.vinshop.repositories;

import com.egfds.vinshop.models.Address;
import com.egfds.vinshop.models.ProductType;
import org.springframework.data.repository.CrudRepository;

public interface IProductTypeRepository extends CrudRepository<ProductType, Long> {
}
