package com.egfds.vinshop.controllers;

import com.egfds.vinshop.models.Cart;
import com.egfds.vinshop.models.User;
import com.egfds.vinshop.services.CartService.ICartItemService;
import com.egfds.vinshop.services.CartService.ICartService;
import com.egfds.vinshop.services.ProductService.IProductService;
import com.egfds.vinshop.services.UserService.IUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        return "redirect:/products/list";
    }

    @GetMapping("/view")
    public String view(Authentication authentication, Model model){
        User user = userService.findByEmail(authentication.getName()).get();
        Cart cart = cartService.getUserCart(user).get();

        model.addAttribute("cart", cart);
        return "cart/view";
    }

}
