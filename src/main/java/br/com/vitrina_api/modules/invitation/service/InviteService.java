package br.com.vitrina_api.modules.invitation.service;

import br.com.vitrina_api.modules.invitation.model.Invite;
import br.com.vitrina_api.modules.invitation.repository.InviteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InviteService {

    private final InviteRepository inviteRepository;

    public InviteService(InviteRepository inviteRepository) {
        this.inviteRepository = inviteRepository;
    }

    @Transactional
    public Invite createInvite(Invite invite){
        var invited = inviteRepository.save(invite);
        return invited;
    }

    public List<Invite> findByStoreId(UUID storeId){
        List<Invite> invites = inviteRepository.findByStoreId(storeId);
        return invites;
    }

    public void acceptInvite(String token){
        Invite invite = inviteRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Token invalido"));

        if(invite.getCreatedAt() == invite.getExpiresAt()){
            throw new RuntimeException("Convite expirou");
        }
    }


}
