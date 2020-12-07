package com.egfds.vinshop.repositories;

import com.egfds.vinshop.models.Address;
import org.springframework.data.repository.CrudRepository;

public interface IAddressRepository extends CrudRepository<Address, Long> {
}
