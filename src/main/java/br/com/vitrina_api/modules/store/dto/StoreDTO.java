package br.com.vitrina_api.modules.store.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class StoreDTO {
    private String name;
    private String slug;
    private String email;
}
