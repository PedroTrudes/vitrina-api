package br.com.vitrina_api.modules.invitation.mapper;

import br.com.vitrina_api.modules.invitation.dto.InviteResponse;
import br.com.vitrina_api.modules.invitation.model.Invite;

public class InviteMapper {
    public static InviteResponse toResponse(Invite invite){
        return new InviteResponse(invite.getToken(), invite.getStore().getName(), invite.getExpiresAt());
    }
}
