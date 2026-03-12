package br.com.vitrina_api.modules.auth.dto;

import br.com.vitrina_api.modules.user.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(

        @NotBlank
        String name,

        @Email
        String email,

        @NotBlank
        String password,

        UserRole role

) {
}
