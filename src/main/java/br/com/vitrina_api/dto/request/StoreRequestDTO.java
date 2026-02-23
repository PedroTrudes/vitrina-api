package br.com.vitrina_api.dto.request;

import br.com.vitrina_api.model.NumberContact;

import java.util.List;

public record StoreRequestDTO(String name, String slug, String logoUrl, String email, String active, Integer plan, List<NumberRequestDTO> contacts) {


}
