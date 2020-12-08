package com.egfds.vinshop.controllers;

import com.egfds.vinshop.models.Address;
import com.egfds.vinshop.models.User;
import com.egfds.vinshop.models.Zip;
import com.egfds.vinshop.services.UserService.IAddressService;
import com.egfds.vinshop.services.UserService.IUserService;
import com.egfds.vinshop.services.UserService.IZipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    IUserService userService;
    IAddressService addressService;
    IZipService zipService;

    public UserController(IUserService userService, IAddressService addressService, IZipService zipService) {
        this.userService = userService;
        this.addressService = addressService;
        this.zipService = zipService;
    }


    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("address", new Address());
        model.addAttribute("zip", new Zip());
        return "user/signup";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute User user, @ModelAttribute Address address, @ModelAttribute Zip zip){
        zipService.save(zip);
        address.setZipCode(zip);

        addressService.save(address);
        user.setAddress(address);

        user.setRoles("ROLE_USER");

        userService.save(user);

        return "redirect:/";
    }

}
