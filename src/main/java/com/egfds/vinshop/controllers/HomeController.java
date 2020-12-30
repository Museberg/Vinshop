package com.egfds.vinshop.controllers;

import com.egfds.vinshop.models.EmailNewsletter;
import com.egfds.vinshop.models.FarmSummary;
import com.egfds.vinshop.services.EmailNewsletterService.IEmailNewsletterService;
import com.egfds.vinshop.services.CartService.ICartService;
import com.egfds.vinshop.services.FarmSummaryService.IFarmSummaryService;
import com.egfds.vinshop.services.UserService.IAddressService;
import com.egfds.vinshop.services.UserService.IUserService;
import com.egfds.vinshop.utillities.ControllerAdvisor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

@Controller
public class HomeController extends ControllerAdvisor {

    IAddressService addressService;
    IFarmSummaryService farmSummaryService;
    IEmailNewsletterService emailNewsletterService;

    public HomeController(IAddressService addressService, IFarmSummaryService farmSummaryService, IUserService userService, ICartService cartService, IEmailNewsletterService emailNewsletterService) {
        super(userService, cartService);
        this.addressService = addressService;
        this.farmSummaryService = farmSummaryService;
        this.emailNewsletterService = emailNewsletterService;
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

    @GetMapping("/sendEmail")
    public String sendEmail(Model model){
        Set<EmailNewsletter> emailNewsletterSet = emailNewsletterService.findAll();
        ArrayList<String> emailAddresses = new ArrayList<>();
        for (EmailNewsletter emailNewsletter: emailNewsletterSet) {
            emailAddresses.add(emailNewsletter.getEmail());
        }
        String emailsCSV = Arrays.toString(emailAddresses.toArray())
                .replace("[", "").replace("]", "");
        model.addAttribute("emailList", emailsCSV);
        return "sendEmail";
    }

    @PostMapping("/addEmailToNewsletterList")
    public String addEmail(@RequestParam("email") String email){
        EmailNewsletter emailNewsletter = new EmailNewsletter(0, email);
        try {
            emailNewsletterService.save(emailNewsletter);
        } catch(Exception e){
            System.out.println(e);
        }
        return "redirect:/";
    }
}
