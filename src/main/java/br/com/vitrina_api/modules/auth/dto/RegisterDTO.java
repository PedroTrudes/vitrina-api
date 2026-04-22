package br.com.vitrina_api.modules.auth.dto;

import br.com.vitrina_api.modules.user.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(

        @NotBlank(message = "Nome é obrigatorio")
        String name,

        @Email(message = "Email inválido")
        @NotBlank(message = "Email é obrigatorio")
        String email,

        @NotBlank(message = "Senha é obrigatoria")
        String password,

        UserRole role

) {
}
