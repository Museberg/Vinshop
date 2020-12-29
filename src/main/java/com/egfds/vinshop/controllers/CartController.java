package com.egfds.vinshop.controllers;

import com.egfds.vinshop.models.Cart;
import com.egfds.vinshop.models.CartItem;
import com.egfds.vinshop.models.User;
import com.egfds.vinshop.services.CartService.ICartItemService;
import com.egfds.vinshop.services.CartService.ICartService;
import com.egfds.vinshop.services.ProductService.IProductService;
import com.egfds.vinshop.services.UserService.IUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {


    private ICartService cartService;
    private ICartItemService cartItemService;
    private IProductService productService;
    private IUserService userService;

    public CartController(ICartService cartService, ICartItemService cartItemService, IProductService productService, IUserService userService) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public String add(@RequestParam("id") Long id, Authentication authentication){
        User user = userService.findByEmail(authentication.getName()).get();

        // If user does not already have a cart, one is made for them
        Cart cart = cartService.getUserCart(user).get();

        // If cart already contains item, amount is incremented. If not, the item is added
        cartService.addItem(cart, productService.findById(id).get());

        return "redirect:/cart/view";
    }

    @GetMapping("/view")
    public String view(Authentication authentication, Model model){
        User user = userService.findByEmail(authentication.getName()).get();
        Cart cart = cartService.getUserCart(user).get();

        model.addAttribute("cart", cart);
        return "cart/view";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam long itemId){
        CartItem cartItem = cartItemService.findById(itemId).get();
        Cart cart = cartItemService.getCartFromCartItem(cartItem);
        // Removing cart item from cart
        cart.getItems().remove(cartItem);
        cartService.save(cart);
        // Deleting cart item from database
        cartItemService.delete(cartItem);
        return "redirect:/cart/view";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Cart cart){
        // Saving each cart item to the database
        for(CartItem ct : cart.getItems()){
            cartItemService.save(ct);
        }
        return "redirect:/cart/view";
    }

}
