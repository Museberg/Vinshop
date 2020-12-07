package com.egfds.vinshop.controllers;

import com.egfds.vinshop.models.ProductAttribute;
import com.egfds.vinshop.models.ProductAttributeValue;
import com.egfds.vinshop.services.Product.IProductAttributeService;
import com.egfds.vinshop.services.Product.IProductAttributeValueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(value = {"/attributeOptions"})
public class ProductAttributeValueController {

    private IProductAttributeService productAttributeService;
    private IProductAttributeValueService productAttributeValueService;

    public ProductAttributeValueController(IProductAttributeService productAttributeService, IProductAttributeValueService productAttributeValueService) {
        this.productAttributeService = productAttributeService;
        this.productAttributeValueService = productAttributeValueService;
    }

    @GetMapping("/create")
    public String create(Model model){
        Set<ProductAttribute> attributes = productAttributeService.findAll();
        System.out.println(attributes.size());
        model.addAttribute("attributes", attributes);
        model.addAttribute("attributeValue", new ProductAttributeValue());
        return "productAttributeValues/create";
    }

    @PostMapping("/create")
    public String add(@RequestParam("attribute") long attributeId, @RequestParam("attributeOption") String text){
        System.out.println("Selected attribute ID: " + attributeId);
        System.out.println("Attribute option: " + text);
        ProductAttributeValue attributeValue = (new ProductAttributeValue(text));
        attributeValue = productAttributeValueService.save(attributeValue);
        System.out.println("Attribute option id after save: " + attributeValue.getId());
        ProductAttribute actualProduct = productAttributeService.findById(attributeId).get();

        actualProduct.getProductAttributeValues().add(attributeValue);
        productAttributeService.save(actualProduct);
        System.out.println("Added option: " + actualProduct.getProductAttributeValues().size());

        for(ProductAttributeValue att : productAttributeService.findById(actualProduct.getId()).get().getProductAttributeValues()){
            System.out.println(att.getValue());
        }
        return "redirect:/attributeOptions/create";
    }
}
