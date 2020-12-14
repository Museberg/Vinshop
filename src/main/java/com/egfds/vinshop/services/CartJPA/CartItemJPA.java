package com.egfds.vinshop.services.CartJPA;

import com.egfds.vinshop.models.Cart;
import com.egfds.vinshop.models.CartItem;
import com.egfds.vinshop.repositories.CartRepos.ICartItemRepo;
import com.egfds.vinshop.repositories.CartRepos.ICartRepo;
import com.egfds.vinshop.services.CartService.ICartItemService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class CartItemJPA implements ICartItemService {

    private final ICartItemRepo cartItemRepo;
    private final ICartRepo cartRepo;
    private final EntityManager entityManager;

    public CartItemJPA(ICartItemRepo cartItemRepo, ICartRepo cartRepo, EntityManager entityManager) {
        this.cartItemRepo = cartItemRepo;
        this.cartRepo = cartRepo;
        this.entityManager = entityManager;
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

    @Override
    public Cart getCartFromCartItem(CartItem cartItem) {
        Query query = entityManager.createNativeQuery(  "SELECT cart_id FROM cart_items WHERE items_id = ?");
        query.setParameter(1, cartItem.getId());
        BigInteger val = (BigInteger) query.getSingleResult();
        return cartRepo.findById(val.longValue()).get();
    }
}
