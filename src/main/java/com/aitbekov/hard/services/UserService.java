package com.aitbekov.hard.services;

import com.aitbekov.hard.models.User;

public interface UserService {
    User registerUser(User user);

    User getUserById(Long id);

    User getUserByUsername(String username);

    User getUserByEmail(String email);
}
