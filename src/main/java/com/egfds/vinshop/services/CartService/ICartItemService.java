package com.egfds.vinshop.services.CartService;

import com.egfds.vinshop.models.Cart;
import com.egfds.vinshop.models.CartItem;
import com.egfds.vinshop.services.ICrudService;

public interface ICartItemService extends ICrudService<CartItem, Long> {
    public Cart getCartFromCartItem(CartItem cartItem);
}
