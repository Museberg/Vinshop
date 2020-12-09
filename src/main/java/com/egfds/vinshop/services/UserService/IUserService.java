package com.egfds.vinshop.services.UserService;

import com.egfds.vinshop.models.User;
import com.egfds.vinshop.services.ICrudService;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface IUserService extends ICrudService<User, Long> {
    Optional<User> findByEmail(String email);
}
