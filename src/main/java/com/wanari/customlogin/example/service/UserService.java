package com.wanari.customlogin.example.service;

import com.wanari.customlogin.example.config.security.constant.Roles;
import com.wanari.customlogin.example.domain.User;
import com.wanari.customlogin.example.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        User detonator = new User();
        detonator.login = "detonator";
        detonator.password = passwordEncoder.encode("detonator");
        detonator.roles.add(Roles.DETONATOR_ROLE);
        detonator.roles.add(Roles.USER_ROLE);
        userRepository.set(detonator.id, detonator);

        User user = new User();
        user.login = "user";
        user.password = passwordEncoder.encode("user");
        user.roles.add(Roles.USER_ROLE);
        userRepository.set(user.id, user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User loggedInUser = findByLogin(login)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + login));

        List<SimpleGrantedAuthority> roles = loggedInUser.roles.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(loggedInUser.login, loggedInUser.password, roles);
    }

    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
