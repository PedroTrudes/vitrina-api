package br.com.vitrina_api.modules.invitation.controller;

import br.com.vitrina_api.modules.invitation.model.Invite;
import br.com.vitrina_api.modules.invitation.repository.InviteRepository;
import br.com.vitrina_api.modules.invitation.service.InviteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/invites")
@RequiredArgsConstructor
public class InviteController {
    private final InviteService inviteService;

    @PostMapping
    public ResponseEntity<Invite> create(@RequestBody Invite invite){
        Invite createInvite = inviteService.createInvite(invite);
        return ResponseEntity.status(HttpStatus.CREATED).body(createInvite);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Invite>> findAllByStoreId(@PathVariable UUID storeId){
        List<Invite> invites = inviteService.findByStoreId(storeId);
        if(invites.isEmpty()){
            throw new RuntimeException("Não foi localizado convites");
        }
        return ResponseEntity.status(HttpStatus.OK).body(invites);
    }


}
