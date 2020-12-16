package com.egfds.vinshop.services.CartJPA;

import com.egfds.vinshop.models.Cart;
import com.egfds.vinshop.models.CartItem;
import com.egfds.vinshop.models.Product;
import com.egfds.vinshop.models.User;
import com.egfds.vinshop.repositories.CartRepos.ICartItemRepo;
import com.egfds.vinshop.repositories.CartRepos.ICartRepo;
import com.egfds.vinshop.services.CartService.ICartService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartJPA implements ICartService {

    private final ICartRepo cartRepo;
    private final ICartItemRepo cartItemRepo;

    public CartJPA(ICartRepo cartRepo, ICartItemRepo cartItemRepo) {
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
    }

    @Override
    public Set<Cart> findAll() {
        Set <Cart> set = new HashSet<>();
        cartRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Cart save(Cart object) {
        return cartRepo.save(object);
    }

    @Override
    public void delete(Cart object) {
        cartRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        cartRepo.deleteById(aLong);
    }

    @Override
    public Optional<Cart> findById(Long aLong) {
        return cartRepo.findById(aLong);
    }

    // Returns cart of user if present, or creates a new one
    @Override
    public Optional<Cart> getUserCart(User user) {
        for(Cart c : cartRepo.findAll()){
            if(c.getUser().getId() == user.getId()){
                return Optional.ofNullable(c);
            }
        }
        Cart cart = new Cart();
        cart.setUser(user);;
        save(cart);
        return Optional.ofNullable(cart);
    }

    @Override
    public void addItem(Cart cart, Product productToAdd) {
        if(cart.getItems() != null){
            for(CartItem item : cart.getItems()){
                if(item.getProduct().getId() == productToAdd.getId()){
                    item.setAmount(item.getAmount() + 1);
                    cartRepo.save(cart);
                    return;
                }
            }
        }
        // If this is reached, the product was not already in the cart
        // Creating cart
        CartItem cartItem = new CartItem();
        // Adding product
        cartItem.setProduct(productToAdd);
        cartItem.setAmount(1);
        // Saving cart item
        cartItemRepo.save(cartItem);

        // If not items have previously been added, the set is null, so we must set it
        if(cart.getItems() == null){
            List<CartItem> set = new ArrayList<>(Arrays.asList(cartItem));
            cart.setItems(set);
        }
        // If cart already contains some items, we simply add it to the set
        else{
            cart.getItems().add(cartItem);
        }

        cartRepo.save(cart);
    }

    @Override
    public Optional<Cart> getCartFromCartItem(CartItem cartItem) {
        for(Cart c : cartRepo.findAll()){
            if(c.getItems().contains(cartItem)){
                return Optional.ofNullable(c);
            }
        }
        return Optional.empty();
    }

}
