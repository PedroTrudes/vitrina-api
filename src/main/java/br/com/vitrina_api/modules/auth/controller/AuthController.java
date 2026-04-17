package br.com.vitrina_api.modules.auth.controller;

import br.com.vitrina_api.modules.auth.dto.AuthResponseDTO;
import br.com.vitrina_api.modules.auth.dto.LoginDTO;
import br.com.vitrina_api.modules.auth.dto.RegisterDTO;
import br.com.vitrina_api.modules.auth.service.AuthService;
import br.com.vitrina_api.modules.invitation.service.InviteService;
import br.com.vitrina_api.modules.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final InviteService inviteService;

    @PostMapping("/register")
    public ResponseEntity<User> regiter(@RequestBody RegisterDTO dto){
        User user = authService.register(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO dto){
        AuthResponseDTO responseDTO = authService.login(dto);

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/register-with-invite")
    public ResponseEntity<Void> RegisterWithInvite(@RequestBody RegisterDTO dto, @RequestParam String token){
        inviteService.registerWithInvite(dto, token);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
