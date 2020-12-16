package com.egfds.vinshop.services.UserJPA;

import com.egfds.vinshop.models.Address;
import com.egfds.vinshop.models.User;
import com.egfds.vinshop.repositories.UserRepos.IAddressRepository;
import com.egfds.vinshop.services.UserService.IAddressService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
        Set<Address> addresses = new HashSet<>();
        IAddressRepository.findAll().forEach(addresses :: add);
        return addresses;
    }

    @Override
    public Address save(Address object) {
        return IAddressRepository.save(object);
    }

    @Override
    public void delete(Address object) {
        IAddressRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        IAddressRepository.deleteById(aLong);
    }

    @Override
    public Optional<Address> findById(Long aLong) {
        return IAddressRepository.findById(aLong);
    }
}
