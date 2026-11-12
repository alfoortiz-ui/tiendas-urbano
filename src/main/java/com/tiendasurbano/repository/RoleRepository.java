package com.tiendasurbano.repository;

import com.tiendasurbano.entity.Role;
import com.tiendasurbano.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
