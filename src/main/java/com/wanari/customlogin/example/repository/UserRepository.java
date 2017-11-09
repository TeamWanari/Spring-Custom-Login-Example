package com.wanari.customlogin.example.repository;

import com.wanari.customlogin.example.domain.User;
import org.springframework.boot.actuate.metrics.util.SimpleInMemoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository extends SimpleInMemoryRepository<User> {

    // Thanks SimpleInMemoryRepository...
    public List<User> findAll() {
        return (List<User>) super.findAll();
    }

    public Optional<User> findByLogin(String login) {
        return findAll().stream()
            .filter(user -> user.login.equals(login))
            .findAny();
    }
}
