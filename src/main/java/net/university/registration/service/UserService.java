package net.university.registration.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.university.registration.model.User;
import net.university.registration.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
