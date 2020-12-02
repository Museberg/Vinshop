package com.egfds.vinshop;

import com.egfds.vinshop.models.Address;
import com.egfds.vinshop.repositories.IUserRepository;
import com.egfds.vinshop.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = IUserRepository.class)
public class VinshopApplication {


    public static void main(String[] args) {
        SpringApplication.run(VinshopApplication.class, args);
    }

}
