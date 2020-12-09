package com.egfds.vinshop.services.CartJPA;

import com.egfds.vinshop.models.CartItem;
import com.egfds.vinshop.repositories.CartRepos.ICartItemRepo;
import com.egfds.vinshop.repositories.CartRepos.ICartRepo;
import com.egfds.vinshop.services.CartService.ICartItemService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class CartItemJPA implements ICartItemService {

    private final ICartItemRepo cartItemRepo;

    public CartItemJPA(ICartItemRepo cartItemRepo) {
        this.cartItemRepo = cartItemRepo;
    }

    @Override
    public Set<CartItem> findAll() {
        Set<CartItem> set = new HashSet<>();
        cartItemRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public CartItem save(CartItem object) {
        return cartItemRepo.save(object);
    }

    @Override
    public void delete(CartItem object) {
        cartItemRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        cartItemRepo.deleteById(aLong);
    }

    @Override
    public Optional<CartItem> findById(Long aLong) {
        return cartItemRepo.findById(aLong);
    }
}
