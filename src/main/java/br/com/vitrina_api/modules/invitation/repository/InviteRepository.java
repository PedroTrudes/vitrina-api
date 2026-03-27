package br.com.vitrina_api.modules.invitation.repository;

import br.com.vitrina_api.modules.invitation.model.Invite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InviteRepository extends JpaRepository<Invite, Long> {
    List<Invite> findByStoreId(UUID storeId);

    Optional<Invite> findByToken(String token);
}
