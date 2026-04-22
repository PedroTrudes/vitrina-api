package br.com.vitrina_api.modules.auth.mapper;

import br.com.vitrina_api.modules.auth.dto.RegisterDTO;
import br.com.vitrina_api.modules.auth.dto.RegisterResponse;
import br.com.vitrina_api.modules.user.model.User;

public class AuthMapper {
    public static RegisterResponse toResponseRegister(User user){
        return new RegisterResponse(user.getName(), user.getEmail());
    }
}
