package com.tiendasurbano.config;

import com.tiendasurbano.entity.*;
import com.tiendasurbano.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.math.BigDecimal;

@Configuration
public class SeedDataConfig {
    @Bean
    @Profile({"local", "test"})
    CommandLineRunner seedData(RoleRepository roles, CategoryRepository categories,
                                ProductRepository products, PasswordEncoder encoder) {
        return args -> {
            for (RoleName roleName : RoleName.values()) {
                roles.findByName(roleName).orElseGet(() -> roles.save(new Role(roleName)));
            }
            Category chaquetas = categories.findByNameIgnoreCase("Chaquetas")
                    .orElseGet(() -> categories.save(new Category("Chaquetas")));
            Category blazers = categories.findByNameIgnoreCase("Blazers")
                    .orElseGet(() -> categories.save(new Category("Blazers")));
            if (products.count() == 0) {
                products.save(new Product("URB-CHA-001", "Chaqueta Aurora",
                        "Chaqueta liviana de corte urbano.", new BigDecimal("39990"),
                        true, true, 8, "S,M,L", "Negro,Beige", chaquetas));
                products.save(new Product("URB-BLA-001", "Blazer Roma",
                        "Blazer versátil para looks de oficina.", new BigDecimal("45990"),
                        true, true, 4, "S,M,L", "Borgoña,Negro", blazers));
                products.save(new Product("URB-OLD-001", "Producto no publicado",
                        "Producto solo para validar reglas de publicación.", new BigDecimal("19990"),
                        false, false, 10, "M", "Azul", chaquetas));
            }
        };
    }
}
