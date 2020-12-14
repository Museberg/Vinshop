package com.egfds.vinshop.services.UserJPA;

import com.egfds.vinshop.models.User;
import com.egfds.vinshop.repositories.UserRepos.IUserRepository;
import com.egfds.vinshop.services.UserService.IUserService;
import org.springframework.stereotype.Service;

import java.util.*;

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
        for(User u : userRepo.findAll()){
            if(u.getEmail().equalsIgnoreCase(email)){
                return Optional.ofNullable(u);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAllByRole(String role) {
        List<User> users = new ArrayList<>();
        role = "ROLE_" + role.toUpperCase();
        for(User u : userRepo.findAll()){
            if(u.getRoles().equals(role)){
                users.add(u);
            }
        }
        return users;
    }
}
