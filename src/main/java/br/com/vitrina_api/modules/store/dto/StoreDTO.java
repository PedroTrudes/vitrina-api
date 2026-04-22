package br.com.vitrina_api.modules.store.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class StoreDTO {

    @NotBlank(message = "Nome é obrigatorio")
    private String name;

    @NotBlank(message = "Slug da loja é obrigatorio")
    private String slug;

    @Email(message = "Email invalido")
    @NotBlank(message = "Email da loja é obrigatorio")
    private String email;
}
