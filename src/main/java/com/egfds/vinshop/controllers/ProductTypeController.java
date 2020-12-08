package com.egfds.vinshop.controllers;

import com.egfds.vinshop.models.Attribute;
import com.egfds.vinshop.models.AttributeList;
import com.egfds.vinshop.models.ProductType;
import com.egfds.vinshop.services.ProductService.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productTypes")
public class ProductTypeController {
    IProductTypeService productTypeService;
    IAttributeService attributeService;

    public ProductTypeController(IProductTypeService productTypeService, IAttributeService attributeService) {
        this.productTypeService = productTypeService;
        this.attributeService = attributeService;
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

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("types", productTypeService.findAll());
        return "productType/list";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("id") long typeId, Model model){
        model.addAttribute("type", productTypeService.findById(typeId).get());
        model.addAttribute("attributeList", new AttributeList(attributeService.getAttributesByType(typeId)));
        return "productType/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute ProductType type, @ModelAttribute AttributeList wrapper){
        for(Attribute att : wrapper.getAttributes()){
            attributeService.save(att);
        }
        productTypeService.save(type);
        return "redirect:/productTypes/list";
    }
}
