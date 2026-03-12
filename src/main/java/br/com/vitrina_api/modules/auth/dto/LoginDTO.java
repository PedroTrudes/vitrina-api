package br.com.vitrina_api.modules.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @Email
        String email,

        @NotBlank
        String password

) {
}
