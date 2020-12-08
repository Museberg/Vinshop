package com.egfds.vinshop.repositories.UserRepos;

import com.egfds.vinshop.models.Address;
import org.springframework.data.repository.CrudRepository;

public interface IAddressRepository extends CrudRepository<Address, Long> {
}
