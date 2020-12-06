package com.egfds.vinshop.services.springdatajpa;

import com.egfds.vinshop.models.Address;
import com.egfds.vinshop.repositories.IAddressRepository;
import com.egfds.vinshop.services.IAddressService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AddressJPA implements IAddressService {

    private final IAddressRepository IAddressRepository;

    public AddressJPA(IAddressRepository IAddressRepository) {
        this.IAddressRepository = IAddressRepository;
    }

    @Override
    public Set<Address> findAll() {
        return null;
    }

    @Override
    public Address save(Address object) {
        return IAddressRepository.save(object);
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
