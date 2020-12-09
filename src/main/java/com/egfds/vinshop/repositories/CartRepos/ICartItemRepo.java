package com.egfds.vinshop.repositories.CartRepos;

import com.egfds.vinshop.models.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface ICartItemRepo extends CrudRepository<CartItem, Long> {
}
