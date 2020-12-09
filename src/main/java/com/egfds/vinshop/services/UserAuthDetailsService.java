package com.egfds.vinshop.services;


import com.egfds.vinshop.models.User;
import com.egfds.vinshop.models.UserAuthDetails;
import com.egfds.vinshop.repositories.UserRepos.IUserRepository;
import com.egfds.vinshop.services.UserService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserAuthDetailsService implements UserDetailsService {
    @Autowired
    IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("2");
        Optional<User> user =  userService.findByEmail(email.toLowerCase());
        user.orElseThrow(() -> new UsernameNotFoundException("No user found with email: " + email));
        System.out.println("HERE IS THE USER : " + user.get());
        return user.map(UserAuthDetails::new).get();
    }
}
