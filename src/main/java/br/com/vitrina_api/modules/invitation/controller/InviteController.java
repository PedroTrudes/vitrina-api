package br.com.vitrina_api.modules.invitation.controller;

import br.com.vitrina_api.modules.invitation.model.Invite;
import br.com.vitrina_api.modules.invitation.repository.InviteRepository;
import br.com.vitrina_api.modules.invitation.service.InviteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/invites")
@RequiredArgsConstructor
public class InviteController {
    private final InviteService inviteService;

    @PostMapping("/store/{storePublicId}")
    public ResponseEntity<Invite> create(@PathVariable UUID storePublicId){
        Invite createInvite = inviteService.create(storePublicId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createInvite);
    }

    @GetMapping("/valid/{token}")
    public ResponseEntity<Invite> validTokenInInvite(@PathVariable String token){
        Invite invite = inviteService.validateInvite(token);
        return ResponseEntity.status(HttpStatus.OK).body(invite);
    }


}
