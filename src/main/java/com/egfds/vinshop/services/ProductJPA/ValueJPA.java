package com.egfds.vinshop.services.ProductJPA;

import com.egfds.vinshop.models.Attribute;
import com.egfds.vinshop.models.Product;
import com.egfds.vinshop.models.ProductType;
import com.egfds.vinshop.models.Value;
import com.egfds.vinshop.repositories.ProductRepos.IAttributeRepo;
import com.egfds.vinshop.repositories.ProductRepos.IProductRepo;
import com.egfds.vinshop.repositories.ProductRepos.IProductTypeRepo;
import com.egfds.vinshop.repositories.ProductRepos.IValueRepo;
import com.egfds.vinshop.services.ProductService.IAttributeService;
import com.egfds.vinshop.services.ProductService.IProductService;
import com.egfds.vinshop.services.ProductService.IValueService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Phaser;

@Service
public class ValueJPA implements IValueService {

    private final IValueRepo valueRepo;
    private final IAttributeRepo attributeRepo;
    private final IProductRepo productRepo;
    private final IProductTypeRepo productTypeRepo;

    public ValueJPA(IValueRepo valueRepo, IAttributeRepo attributeRepo, IProductRepo productRepo, IProductTypeRepo productTypeRepo) {
        this.valueRepo = valueRepo;
        this.attributeRepo = attributeRepo;
        this.productRepo = productRepo;
        this.productTypeRepo = productTypeRepo;
    }

    @Override
    public Set<Value> findAll() {
        Set<Value> set = new HashSet<>();
        valueRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Value save(Value object) {
        return valueRepo.save(object);
    }

    @Override
    public void delete(Value object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<Value> findById(Long aLong) {
        return valueRepo.findById(aLong);
    }

    @Override
    public void deleteByProductId(Long productId) {
        List<Value> values = new ArrayList<>();
        valueRepo.findAll().forEach(values::add);
        for(Value v : values){
            if(v.getProduct().getId() == productId){
                valueRepo.delete(v);
            }
        }

    }

    @Override
    public List<Value> getByProductId(Long productId) {
        List<Value> returnValues = new ArrayList<>();
        long typeId = -1;
        for(Value v : valueRepo.findAll()){
            if(v.getProduct().getId() == productId){
                if(typeId == -1){ // Used later
                    typeId = v.getType().getId();
                }
                returnValues.add(v);
            }
        }
        // Sometimes a new attribute may be added to the product type after a product has been created from it.
        // Adding those new attributes here:
        List<Attribute> missingAttributes = new ArrayList<>();
        outerloop:
        for(Attribute a : attributeRepo.findAll()){
            for(Value v : returnValues){
                // If attribute is already in list of values, we simply skip to next attribute
                if(a.getId() == v.getAttribute().getId()){
                    continue outerloop;
                }
            }
            // If the attribute could not be found in the list of values, we add it the list of missing attributes
            missingAttributes.add(a);
        }

        Product product = productRepo.findById(productId).get();
        ProductType type = productTypeRepo.findById(typeId).get();
        for(Attribute a : missingAttributes){
            if(a.getType().getId() == typeId){
                // Creating empty values for the missing attributes
                Value value = new Value(0, product, type, a, "");
                returnValues.add(value);
            }
        }
        return returnValues;
    }

    @Override
    public void deleteByAttributeId(Long attributeId) {
        for(Value v : valueRepo.findAll()){
            if(v.getAttribute().getId() == attributeId){
                valueRepo.delete(v);
            }
        }
    }
}
