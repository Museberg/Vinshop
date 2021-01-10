package com.egfds.vinshop.utillities;

import com.egfds.vinshop.models.Cart;
import com.egfds.vinshop.models.User;
import com.egfds.vinshop.services.CartService.ICartService;
import com.egfds.vinshop.services.UserService.IUserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    IUserService userService;
    ICartService cartService;

    public ControllerAdvisor(IUserService userService, ICartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    public ControllerAdvisor() {
    }

    @ModelAttribute("userCart")
    public Cart getUserCart(Authentication auth) {
        if(auth != null) {
            User activeUser = userService.findByEmail(auth.getName()).get();
            Cart userCart = cartService.getUserCart(activeUser).get();
            return userCart;
        }
        return null;
    }

    @ModelAttribute("userRole")
    public String getUserRole(Authentication auth){
        if(auth != null){
            String email = auth.getName();
            String role = userService.findByEmail(email).get().getRoles();
            return role.substring(5);
        }
        return null;
    }

    @ModelAttribute("userName")
    public String getUserName(Authentication auth){
        if(auth != null){
            return auth.getName();
        }
        return null;
    }

}