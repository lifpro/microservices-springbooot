package com.technolab.auth.services;
import com.technolab.auth.models.User;
import com.technolab.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User findByEmail(String email) {
        try {
            return repository.findByEmail(email).get();
        } catch (Exception e) {
            return null;
        }
    }
}
