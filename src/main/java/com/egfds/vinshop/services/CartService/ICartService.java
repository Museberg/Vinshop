package com.egfds.vinshop.services.CartService;

import com.egfds.vinshop.models.Cart;
import com.egfds.vinshop.models.Product;
import com.egfds.vinshop.models.User;
import com.egfds.vinshop.services.ICrudService;

import java.util.Optional;

public interface ICartService extends ICrudService<Cart, Long> {
    public Optional<Cart> getUserCart(User user);
    public void addItem(Cart cart, Product productToAdd);
}
