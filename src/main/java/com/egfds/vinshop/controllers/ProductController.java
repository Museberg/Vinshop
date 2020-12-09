package com.egfds.vinshop.controllers;

import com.egfds.vinshop.models.*;
import com.egfds.vinshop.services.ProductService.IAttributeService;
import com.egfds.vinshop.services.ProductService.IProductService;
import com.egfds.vinshop.services.ProductService.IProductTypeService;
import com.egfds.vinshop.services.ProductService.IValueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private IProductTypeService typeService;
    private IAttributeService attributeService;
    private IProductService productService;
    private IValueService valueService;

    public ProductController(IProductTypeService typeService, IAttributeService attributeService, IProductService productService, IValueService valueService) {
        this.typeService = typeService;
        this.attributeService = attributeService;
        this.productService = productService;
        this.valueService = valueService;
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("types", typeService.findAll());
        return "product/create";
    }

    @PostMapping("/create")
    public String create(Model model, @RequestParam long type){
        System.out.println("TYPE ID: " + type);
        if(type == 0){ // No product type chosen
            return "redirect:/products/create";
        }
        model.addAttribute("product", new Product());
        model.addAttribute("types", typeService.findAll());

        List<Attribute> attributes = attributeService.getAttributesByType(type);
        // Creating a value object for each attribute. Value table contains the values for the attributes
        Value[] values = new Value[attributes.size()];

        // Adding all the stuff required for a value
        for(int i = 0; i < values.length; i++){
            values[i] = new Value();
            values[i].setProduct(new Product());
            values[i].setType(typeService.findById(type).get());
            values[i].setAttribute(attributes.get(i));
        }
        model.addAttribute("valueList", new ValueList(Arrays.asList(values)));
        return "product/create";
    }

    @PostMapping("/add")
    public String add(Model model, @ModelAttribute ValueList wrapper, @ModelAttribute Product product){
        model.addAttribute("types", typeService.findAll());
        for(Value v : wrapper.getValues()){
            System.out.println(v);
        }
        System.out.println(product);

        Product newProduct = productService.save(product);
        // We now have a list of values with type, attribute and value. We just need to add the product to them
        for(Value v : wrapper.getValues()){
            v.setProduct(newProduct);
            // Now that the product is saved to the value, we can save it to the db
            valueService.save(v);
        }
        return "redirect:/products/create";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("products", productService.findAll());
        return "product/list";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") Long id, Model model){
        model.addAttribute("product", productService.findById(id).get());
        model.addAttribute("valueList", new ValueList(valueService.getByProductId(id))); // Values of the product
        return "product/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute ValueList valueList){
        for(Value v : valueList.getValues()){
            System.out.println(v);
            valueService.save(v);
        }
        return "redirect:/products/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id){
        valueService.deleteByProductId(id);
        productService.deleteById(id);
        return "redirect:/products/list";
    }

    @GetMapping("/admin/list")
    public String adminList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "/admin/list";
    }
}
