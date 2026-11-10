package com.tiendasurbano.dto;

import jakarta.validation.constraints.*;

public record RegisterUserRequest(
        @NotBlank @Size(max = 120) String name,
        @NotBlank @Pattern(regexp = "^[0-9.\\-kK]{7,12}$") String rut,
        @NotBlank @Email @Size(max = 160) String email,
        @NotBlank @Size(max = 30) String phone,
        @NotBlank @Size(min = 8, max = 72) String password) {
}
