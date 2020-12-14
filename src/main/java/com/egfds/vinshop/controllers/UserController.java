package com.egfds.vinshop.controllers;

import com.egfds.vinshop.models.Address;
import com.egfds.vinshop.models.User;
import com.egfds.vinshop.models.Zip;
import com.egfds.vinshop.services.UserService.IAddressService;
import com.egfds.vinshop.services.UserService.IUserService;
import com.egfds.vinshop.services.UserService.IZipService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    @GetMapping("/owner/updateRoles")
    public String updateRoles(Model model, Authentication authentication){
        // Removing logged in user from list of users
        User loggedInUser = userService.findByEmail(authentication.getName()).get();
        List<User> users = new ArrayList<>();
        users.addAll(userService.findAll());
        users.remove(loggedInUser);

        Collections.sort(users); // Sorting by role

        model.addAttribute("users", users);
        return "user/updateRoles";
    }

    @PostMapping(value= "/owner/updateRoles")
    public String updateRoles(@RequestParam String newRole){
        long userId = Long.parseLong(newRole.substring(0, 1));
        newRole = newRole.substring(1);

        User user = userService.findById(userId).get();
        user.setRoles(newRole);
        userService.save(user);

        return "redirect:/owner/updateRoles";
    }

}
