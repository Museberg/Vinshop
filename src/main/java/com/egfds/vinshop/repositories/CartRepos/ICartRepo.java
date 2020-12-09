package com.egfds.vinshop.repositories.CartRepos;

import com.egfds.vinshop.models.Cart;
import org.springframework.data.repository.CrudRepository;

public interface ICartRepo extends CrudRepository<Cart, Long> {
}
