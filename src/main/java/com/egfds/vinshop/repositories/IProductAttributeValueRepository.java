package com.egfds.vinshop.repositories;

import com.egfds.vinshop.models.Address;
import com.egfds.vinshop.models.ProductAttributeValue;
import org.springframework.data.repository.CrudRepository;

public interface IProductAttributeValueRepository extends CrudRepository<ProductAttributeValue, Long> {
}
