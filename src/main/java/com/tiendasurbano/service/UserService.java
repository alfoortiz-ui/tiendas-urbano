package com.tiendasurbano.service;

import com.tiendasurbano.dto.RegisterUserRequest;
import com.tiendasurbano.entity.*;
import com.tiendasurbano.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository users;
    private final RoleRepository roles;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository users, RoleRepository roles, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.roles = roles;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(RegisterUserRequest request) {
        if (users.existsByEmailIgnoreCase(request.email())) {
            throw new IllegalArgumentException("No fue posible crear la cuenta con esos datos.");
        }
        if (users.existsByRut(request.rut())) {
            throw new IllegalArgumentException("No fue posible crear la cuenta con esos datos.");
        }
        Role clientRole = roles.findByName(RoleName.CLIENTE)
                .orElseThrow(() -> new IllegalStateException("El rol CLIENTE no está configurado."));
        users.save(new User(request.name().trim(), request.rut().trim(), request.email().trim(),
                request.phone().trim(), passwordEncoder.encode(request.password()), clientRole));
    }
}
