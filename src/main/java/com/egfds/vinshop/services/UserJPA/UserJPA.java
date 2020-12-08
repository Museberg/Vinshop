package com.egfds.vinshop.services.UserJPA;

import com.egfds.vinshop.models.User;
import com.egfds.vinshop.repositories.UserRepos.IUserRepository;
import com.egfds.vinshop.services.UserService.IUserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service
public class UserJPA implements IUserService {


    private final IUserRepository userRepo;

    public UserJPA(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Set<User> findAll() {
        Set<User> users = new HashSet<>();
        userRepo.findAll().forEach(users :: add);
        return users;
    }

    @Override
    public User save(User object) {
        return userRepo.save(object);
    }

    @Override
    public void delete(User object) {
        userRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        userRepo.deleteById(aLong);
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return userRepo.findById(aLong);
    }

    public Optional<User> findByEmail(String email){
        return userRepo.findByEmail(email);
    }
}
