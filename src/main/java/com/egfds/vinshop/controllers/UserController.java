package com.egfds.vinshop.controllers;

import com.egfds.vinshop.models.Address;
import com.egfds.vinshop.models.User;
import com.egfds.vinshop.models.Zip;
import com.egfds.vinshop.services.UserService.IAddressService;
import com.egfds.vinshop.services.UserService.IUserService;
import com.egfds.vinshop.services.UserService.IZipService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/user/about-me")
    public String user(Model model, Authentication auth) {
        String email = auth.getName();
        User user = userService.findByEmail(email).get();
        Address address = addressService.findById(user.getAddress().getId()).get();
        Zip zip = zipService.findById(address.getZip().getId()).get();

        model.addAttribute("user", user);
        model.addAttribute("address", address);
        model.addAttribute("zip", zip);
        return "user/about-me";
    }

    @PostMapping("/user/update")
    public String update(@RequestParam("user_id") long userId, @ModelAttribute User user, @ModelAttribute Address address, @ModelAttribute Zip zip) {
        user.setId(userId);

        System.out.println(user);
        System.out.println(address);
        System.out.println(zip);

        zipService.save(zip);
        addressService.save(address);
        userService.save(user);
        return "redirect:/";
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
        address.setZip(zip);

        addressService.save(address);
        user.setAddress(address);

        user.setRoles("ROLE_USER");

        userService.save(user);

        return "redirect:/";
    }

    @GetMapping("user/password-reset")
    public String passwordReset() {
        return "user/password-reset";
    }

    @PostMapping("user/update-password")
    public String updatePassword(Authentication auth, @RequestParam(required = false, name="newPassword") String newPassword, @RequestParam(required = false, name="password") String password) {
        String email = auth.getName();
        User user = userService.findByEmail(email).get();

        if(!user.getPassword().equals(password)) {
            return "redirect:/user/password-reset";
        }

        user.setPassword(newPassword);
        userService.save(user);
        return "redirect:/";
    }

}
