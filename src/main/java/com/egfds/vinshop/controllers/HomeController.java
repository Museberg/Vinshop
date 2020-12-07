package com.egfds.vinshop.controllers;

import com.egfds.vinshop.models.Address;
import com.egfds.vinshop.services.IAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    IAddressService addressService;

    public HomeController(IAddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/")
    public String index(){
        Address address = new Address();
        addressService.save(address);
        return "index";
    }


    @GetMapping("/admin")
    public String admin(){
        return "admin/admin";
    }

    @GetMapping("/owner")
    public String owner() {
        return "owner/owner";
    }

    @GetMapping("/user")
    public String user() {
        return "user/user";
    }
}
