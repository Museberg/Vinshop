package com.egfds.vinshop.controllers;

import com.egfds.vinshop.models.Attribute;
import com.egfds.vinshop.models.AttributeList;
import com.egfds.vinshop.models.ProductType;
import com.egfds.vinshop.services.ProductService.*;
import org.springframework.core.style.StylerUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productTypes")
public class ProductTypeController {
    IProductTypeService productTypeService;
    IAttributeService attributeService;
    IValueService valueService;

    public ProductTypeController(IProductTypeService productTypeService, IAttributeService attributeService, IValueService valueService) {
        this.productTypeService = productTypeService;
        this.attributeService = attributeService;
        this.valueService = valueService;
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

    @PostMapping("/deleteAttribute")
    public String deleteAttribute(@RequestParam long attributeId, @ModelAttribute ProductType type, Model model){
        System.out.println("ATTRIBUTE ID: " + attributeId);
        // Removing all values linked to the attribute
        valueService.deleteByAttributeId(attributeId);
        // Removing attribute
        attributeService.deleteById(attributeId);

        model.addAttribute("type", type);
        model.addAttribute("attributeList", new AttributeList(attributeService.getAttributesByType(type.getId())));
        return "productType/edit";
    }
}
