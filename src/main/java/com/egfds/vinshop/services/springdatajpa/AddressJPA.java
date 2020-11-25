package com.egfds.vinshop.services.springdatajpa;

import com.egfds.vinshop.models.Address;
import com.egfds.vinshop.repositories.AddressRepository;
import com.egfds.vinshop.services.AddressService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AddressJPA implements AddressService {

    private final AddressRepository addressRepository;

    public AddressJPA(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Set<Address> findAll() {
        return null;
    }

    @Override
    public Address save(Address object) {
        return addressRepository.save(object);
    }

    @Override
    public void delete(Address object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<Address> findById(Long aLong) {
        return Optional.empty();
    }
}
