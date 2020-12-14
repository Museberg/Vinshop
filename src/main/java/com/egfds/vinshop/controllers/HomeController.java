package com.egfds.vinshop.controllers;

import com.egfds.vinshop.models.Address;
import com.egfds.vinshop.models.FarmSummary;
import com.egfds.vinshop.services.FarmSummaryService.IFarmSummaryService;
import com.egfds.vinshop.services.UserService.IAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/aboutFarm/edit")
    public String editFarmInfo(Model model){
        model.addAttribute("farmSummary", farmSummaryService.findById((long) 1).get());
        return "aboutFarm/edit";
    }

    @PostMapping("/update")
    public String updateFarmInfo(@ModelAttribute FarmSummary farmSummary, @RequestParam("farmSummaryId") Long id) {
        System.out.println("Here is the ID: " + id);
        FarmSummary temp = farmSummaryService.findById(id).get();
        temp.setAboutFarm(farmSummary.getAboutFarm());
        farmSummaryService.save(temp);
        System.out.println(temp.toString());
        return "redirect:/";
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
