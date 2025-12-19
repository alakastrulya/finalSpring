package com.example.finalspring.service;

import com.example.finalspring.entity.Permission;
import com.example.finalspring.entity.User;
import com.example.finalspring.repository.PermissionRep;
import com.example.finalspring.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MyUserService implements UserDetailsService {
    @Autowired
    private UserRep userRep;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PermissionRep permissionRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRep.findByLogin(username);

        if (Objects.nonNull(user)) {
            return user;
        }

        throw new UsernameNotFoundException("User Not Found");
    }

    public void registr(User user){
        User check = userRep.findByLogin(user.getLogin());

        if (check == null){
            List<Permission> permissions = List.of(permissionRep.findByName("ROLE_USER"));
            user.setPermissions(permissions);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRep.save(user);
        }
    }
}