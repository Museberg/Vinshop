package com.egfds.vinshop.repositories.ProductRepos;

import com.egfds.vinshop.models.Attribute;
import org.springframework.data.repository.CrudRepository;

public interface IAttributeRepo extends CrudRepository<Attribute, Long> {
}
