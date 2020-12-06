package com.egfds.vinshop.services.ProductJPA;

import com.egfds.vinshop.models.Value;
import com.egfds.vinshop.repositories.ProductRepos.IValueRepo;
import com.egfds.vinshop.services.ProductService.IValueService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
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
}
