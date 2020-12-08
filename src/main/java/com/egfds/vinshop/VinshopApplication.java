package com.egfds.vinshop;

import com.egfds.vinshop.repositories.ProductRepos.IProductTypeRepo;
import com.egfds.vinshop.repositories.UserRepos.IUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {IUserRepository.class, IProductTypeRepo.class})
public class VinshopApplication {


    public static void main(String[] args) {
        SpringApplication.run(VinshopApplication.class, args);
    }

}
