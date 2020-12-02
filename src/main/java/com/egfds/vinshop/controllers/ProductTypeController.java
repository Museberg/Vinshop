package com.egfds.vinshop.controllers;

import com.egfds.vinshop.models.ProductAttribute;
import com.egfds.vinshop.models.ProductType;
import com.egfds.vinshop.services.Product.IProductAttributeService;
import com.egfds.vinshop.services.Product.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(value = {"/productTypes"})
public class ProductTypeController {

    private IProductAttributeService productAttributeService;
    private IProductTypeService productTypeService;

    public ProductTypeController(IProductAttributeService productAttributeService, IProductTypeService productTypeService) {
        this.productAttributeService = productAttributeService;
        this.productTypeService = productTypeService;
    }

    @GetMapping("/create")
    public String create(Model model){
        Set<ProductAttribute> attributes = productAttributeService.findAll();
        //System.out.println(attributes.size());
        model.addAttribute("attributes", attributes);
        model.addAttribute("productType", new ProductType());
        //model.addAttribute("productAttributes", new HashSet<Integer>());
        return "/productTypes/create";
    }

    @PostMapping("/create")
    public String add(@ModelAttribute ProductType productType){
        System.out.println("Reached create post mapping");
        System.out.println(productType.getName());
        productTypeService.save(productType);



        Optional<ProductType> productTypeOptional = productTypeService.findById(productType.getId());
        for(ProductAttribute pr : productTypeOptional.get().getProductAttributes()){
            System.out.println(pr.getName());
        }

        System.out.println("Product type ID: " + productType.getId());

        return "redirect:/productTypes/create";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") long id, Model model){
        Optional<ProductType> productType = productTypeService.findById(id);
        model.addAttribute("productType", productType.get());
        for(ProductAttribute pr : productType.get().getProductAttributes()){
            System.out.println(pr.getName());
        }
        return "/productTypes/view";
    }


}
