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

import java.util.Optional;

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
        Optional<FarmSummary> optionalFarmSummary = farmSummaryService.findById((long) 1);
        if(optionalFarmSummary.isEmpty()){
            FarmSummary farmSummary = new FarmSummary();
            farmSummaryService.save(farmSummary);
            model.addAttribute("farmSummary", farmSummary);
        }
        else{
            model.addAttribute("farmSummary", optionalFarmSummary.get());
        }
        return "index";
    }

    @GetMapping("/aboutFarm/edit")
    public String editFarmInfo(Model model){
        Optional<FarmSummary> optionalFarmSummary = farmSummaryService.findById((long) 1);
        if(optionalFarmSummary.isEmpty()){
            FarmSummary farmSummary = new FarmSummary();
            farmSummaryService.save(farmSummary);
            model.addAttribute("farmSummary", farmSummary);
        }
        else{
            model.addAttribute("farmSummary", optionalFarmSummary.get());
        }
        return "aboutFarm/edit";
    }

    @PostMapping("/update")
    public String updateFarmInfo(@ModelAttribute FarmSummary farmSummary) {
        farmSummaryService.save(farmSummary);
        /*FarmSummary temp = farmSummaryService.findById(farmSummary.getId()).get();
        temp.setAboutFarm(farmSummary.getAboutFarm());
        farmSummaryService.save(temp);*/
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
}
