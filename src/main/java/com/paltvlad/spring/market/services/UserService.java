package com.paltvlad.spring.market.services;


import com.paltvlad.spring.market.entities.User;
import com.paltvlad.spring.market.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
