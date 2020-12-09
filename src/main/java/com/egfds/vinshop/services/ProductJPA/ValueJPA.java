package com.egfds.vinshop.services.ProductJPA;

import com.egfds.vinshop.models.Value;
import com.egfds.vinshop.repositories.ProductRepos.IValueRepo;
import com.egfds.vinshop.services.ProductService.IValueService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Phaser;

@Service
public class ValueJPA implements IValueService {

    private final IValueRepo valueRepo;

    public ValueJPA(IValueRepo valueRepo) {
        this.valueRepo = valueRepo;
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
        for(Value v : valueRepo.findAll()){
            if(v.getProduct().getId() == productId){
                returnValues.add(v);
            }
        }
        return returnValues;
    }
}
