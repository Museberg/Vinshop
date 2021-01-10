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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        addFarmSummaryToModel(model);
        return "index";
    }


    @GetMapping("/owner/aboutFarm/edit")
    public String editFarmInfo(Model model){
        addFarmSummaryToModel(model);
        return "aboutFarm/edit";
    }

    public void addFarmSummaryToModel(Model model) {
        Optional<FarmSummary> optionalFarmSummary = farmSummaryService.findById((long) 1);
        if(optionalFarmSummary.isEmpty()){
            FarmSummary farmSummary = new FarmSummary();
            farmSummaryService.save(farmSummary);
            model.addAttribute("farmSummary", farmSummary);
        }else{
            model.addAttribute("farmSummary", optionalFarmSummary.get());
        }
    }

    @PostMapping("/owner/aboutFarm/update")
    public String updateFarmInfo(@ModelAttribute FarmSummary farmSummary, @RequestParam("filesToUpload") MultipartFile[] files) throws IOException {
        farmSummaryService.save(farmSummary);
        farmSummaryService.savePicturesToDirectory(files);
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

    @GetMapping("/owner/sendNewsletter")
    public String sendEmail(Model model){
        Set<EmailNewsletter> emailNewsletterSet = emailNewsletterService.findAll();
        String emailsCSV = emailNewsletterService.convertEmailSetToCSV(emailNewsletterSet);
        model.addAttribute("emailList", emailsCSV);
        return "owner/sendNewsletter";
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
