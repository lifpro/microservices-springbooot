package com.technolab.auth.services;
import com.technolab.auth.models.User;
import com.technolab.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User findByEmail(String email) {
        try {
            System.out.println(email);
            Optional<User> o= this.repository.findByEmail(email);
            if(o.isPresent()) {
                return o.get();
            } else {
                System.out.println("no user");
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
