package com.egfds.vinshop.controllers;

import com.egfds.vinshop.models.*;
import com.egfds.vinshop.services.CartService.ICartItemService;
import com.egfds.vinshop.services.CartService.ICartService;
import com.egfds.vinshop.services.ProductService.*;
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
    private IStockService stockService;
    private ICartItemService cartItemService;

    public ProductController(IProductTypeService typeService, IAttributeService attributeService, IProductService productService, IValueService valueService, IStockService stockService, ICartItemService cartItemService) {
        this.typeService = typeService;
        this.attributeService = attributeService;
        this.productService = productService;
        this.valueService = valueService;
        this.stockService = stockService;
        this.cartItemService = cartItemService;
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("selectedType", (long) 0);
        return "product/create";
    }

    @PostMapping("/create")
    public String create(Model model, @RequestParam long type){
        if(type == 0){ // No product type chosen
            return "redirect:/products/create";
        }
        model.addAttribute("product", new Product());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("selectedType", type);

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

        Product newProduct = productService.save(product);
        // We now have a list of values with type, attribute and value. We just need to add the product to them
        if(wrapper.getValues() != null){
            for(Value v : wrapper.getValues()){
                v.setProduct(newProduct);
                // Now that the product is saved to the value, we can save it to the db
                valueService.save(v);
            }
        }

        // Setting empty stock for product
        Stock tempStock = new Stock();
        tempStock.setStockAmount(0);
        tempStock.setProduct(newProduct);
        stockService.save(tempStock);

        return "redirect:/products/create";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("products", productService.findAll());
        List<Stock> stock = new ArrayList<>();
        stock.addAll(stockService.findAll());
        Collections.sort(stock);
        model.addAttribute("stock", stock);
        return "product/list";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") Long id, Model model){
        model.addAttribute("product", productService.findById(id).get());
        model.addAttribute("valueList", new ValueList(valueService.getByProductId(id))); // Values of the product
        return "product/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute ValueList wrapper, @ModelAttribute Product product){
        if(wrapper.getValues() != null){
            for(Value v : wrapper.getValues()){
                valueService.save(v);
            }
        }
        productService.save(product);
        return "redirect:/products/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id){
        cartItemService.deleteCartItemByProductId(id);
        valueService.deleteByProductId(id);
        stockService.deleteByProductId(id);
        productService.deleteById(id);
        return "redirect:/products/list";
    }

    @GetMapping("/admin/list")
    public String adminList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "/admin/list";
    }

    // Stock

    @PostMapping("stock/edit")
    public String updateStock(@RequestParam("stockId") long id, @RequestParam("stockAmount") int stockAmount){
        Stock temp = stockService.findById(id).get();
        temp.setStockAmount(stockAmount);
        stockService.save(temp);
        return "redirect:/products/list";
    }

    // Product page
    @GetMapping("/view/{id}")
    public String viewProduct(@PathVariable("id") Long id, Model model){
        Product product = productService.findById(id).get();
        List<Value> values = valueService.getByProductId(product.getId());

        model.addAttribute("product", product);
        model.addAttribute("values", values);
        model.addAttribute("type", values.get(0).getType().getLabel());
        return "product/view";
    }

    // Product list for customer
    @GetMapping("/allProducts")
    public String allProducts(Model model){
        List<Product> products = new ArrayList<>(productService.findAll());

        model.addAttribute("products", products);
        return "product/allProducts";
    }
}
