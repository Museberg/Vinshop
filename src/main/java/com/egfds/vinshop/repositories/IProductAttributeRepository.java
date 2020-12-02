package com.egfds.vinshop.repositories;

import com.egfds.vinshop.models.Address;
import com.egfds.vinshop.models.ProductAttribute;
import org.springframework.data.repository.CrudRepository;

public interface IProductAttributeRepository extends CrudRepository<ProductAttribute, Long> {
}
