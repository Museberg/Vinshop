package com.egfds.vinshop.controllers;

import com.egfds.vinshop.models.Address;
import com.egfds.vinshop.services.FarmSummaryService.IFarmSummaryService;
import com.egfds.vinshop.services.UserService.IAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    IAddressService addressService;
    IFarmSummaryService farmSummaryService;

    public HomeController(IAddressService addressService, IFarmSummaryService farmSummaryService) {
        this.addressService = addressService;
        this.farmSummaryService = farmSummaryService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("farmSummary", farmSummaryService.findById((long) 1).get());
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
