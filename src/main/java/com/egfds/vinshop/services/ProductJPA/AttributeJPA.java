package com.egfds.vinshop.services.ProductJPA;

import com.egfds.vinshop.models.Attribute;
import com.egfds.vinshop.repositories.ProductRepos.IAttributeRepo;
import com.egfds.vinshop.services.ProductService.IAttributeService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AttributeJPA implements IAttributeService {

    private final IAttributeRepo attributeRepo;

    public AttributeJPA(IAttributeRepo attributeRepo) {
        this.attributeRepo = attributeRepo;
    }

    @Override
    public Set<Attribute> findAll() {
        Set<Attribute> set = new HashSet<>();
        attributeRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Attribute save(Attribute object) {
        return attributeRepo.save(object);
    }

    @Override
    public void delete(Attribute object) {
        attributeRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        attributeRepo.deleteById(aLong);
    }

    @Override
    public Optional<Attribute> findById(Long aLong) {
        return attributeRepo.findById(aLong);
    }

    @Override
    public List<Attribute> getAttributesByType(long typeId) {
        List<Attribute> attributes = new ArrayList<>();
        attributeRepo.findAll().forEach(attributes::add);
        return attributes.stream().filter(a -> a.getType().getId() == typeId).collect(Collectors.toList());
    }

}
