package com.egfds.vinshop.controllers;

import com.egfds.vinshop.models.ProductType;
import com.egfds.vinshop.repositories.ProductRepos.IProductTypeRepo;
import com.egfds.vinshop.services.ProductService.IProductTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productTypes")
public class ProductTypeController {
    IProductTypeService productTypeService;

    public ProductTypeController(IProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("productType", new ProductType());
        return "productType/create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute ProductType productType){
        productTypeService.save(productType);
        return "redirect:/productTypes/create";
    }

}
