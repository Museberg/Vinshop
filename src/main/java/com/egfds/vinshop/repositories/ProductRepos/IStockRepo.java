package com.egfds.vinshop.repositories.ProductRepos;

import com.egfds.vinshop.models.Stock;
import org.springframework.data.repository.CrudRepository;

public interface IStockRepo extends CrudRepository<Stock, Long> {
}
