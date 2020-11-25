package com.egfds.vinshop.controllers;

import com.egfds.vinshop.models.Address;
import com.egfds.vinshop.services.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    AddressService addressService;

    public HomeController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/")
    public String index(){
        Address address = new Address();
        addressService.save(address);
        return "index";
    }
}
