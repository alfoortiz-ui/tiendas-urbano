package com.tiendasurbano.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120)
    private String name;
    @Column(nullable = false, unique = true, length = 20)
    private String rut;
    @Column(nullable = false, unique = true, length = 160)
    private String email;
    @Column(nullable = false, length = 30)
    private String phone;
    @Column(nullable = false)
    private String passwordHash;
    @Column(nullable = false)
    private boolean enabled = true;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    protected User() {
    }

    public User(String name, String rut, String email, String phone, String passwordHash, Role role) {
        this.name = name;
        this.rut = rut;
        this.email = email.toLowerCase();
        this.phone = phone;
        this.passwordHash = passwordHash;
        this.roles.add(role);
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getRut() { return rut; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getPasswordHash() { return passwordHash; }
    public boolean isEnabled() { return enabled; }
    public Set<Role> getRoles() { return roles; }
}
