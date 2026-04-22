package br.com.vitrina_api.modules.auth.service;

import br.com.vitrina_api.modules.auth.dto.AuthResponseDTO;
import br.com.vitrina_api.modules.auth.dto.LoginDTO;
import br.com.vitrina_api.modules.auth.dto.RegisterDTO;
import br.com.vitrina_api.modules.user.model.User;
import br.com.vitrina_api.modules.user.model.UserRole;
import br.com.vitrina_api.modules.user.repository.UserRepository;
import br.com.vitrina_api.shared.exception.BusinessException;
import br.com.vitrina_api.shared.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public User register(RegisterDTO dto){
        if(userRepository.findByEmail(dto.email()).isPresent()){
            throw new BusinessException("Email já cadastrado", ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .role(UserRole.ADMIN)
                .build();

        return userRepository.save(user);
    }

    public AuthResponseDTO login(LoginDTO dto){
        User user = userRepository.findByEmail(dto.email()).orElseThrow(() -> new BusinessException("Usuario não localizado", ErrorCode.INVALID_CREDENTIALS));

        if(!passwordEncoder.matches(dto.password(), user.getPassword())){
            throw new BusinessException("Senha invalida", ErrorCode.INVALID_CREDENTIALS);
        }

        String token = jwtService.generateToken(user);
        return new AuthResponseDTO(token);
    }
}
