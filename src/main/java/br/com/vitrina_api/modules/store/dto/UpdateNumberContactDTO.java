package br.com.vitrina_api.modules.store.dto;

public record UpdateNumberContactDTO(String countryCode,
        String areaCode,
        String number,
        String agent,
        Boolean active) {

}
