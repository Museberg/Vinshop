package com.egfds.vinshop.controllers;

import com.egfds.vinshop.models.Attribute;
import com.egfds.vinshop.models.ProductType;
import com.egfds.vinshop.services.ProductService.IAttributeService;
import com.egfds.vinshop.services.ProductService.IProductTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;

@Controller
@RequestMapping("/attributes")
public class AttributeController {
    IProductTypeService productTypeService;
    IAttributeService attributeService;

    public AttributeController(IProductTypeService productTypeService, IAttributeService attributeService) {
        this.productTypeService = productTypeService;
        this.attributeService = attributeService;
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("productTypes", productTypeService.findAll());
        model.addAttribute("attribute", new Attribute());
        return "attribute/create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute Attribute attribute, @RequestParam Long productTypeId){
        attribute.setType(productTypeService.findById(productTypeId).get());
        attributeService.save(attribute);
        return "redirect:/attributes/create";
    }
}
