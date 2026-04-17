package br.com.vitrina_api.modules.invitation.repository;

import br.com.vitrina_api.modules.invitation.model.Invite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InviteRepository extends JpaRepository<Invite, Long> {

    Optional<Invite> findByToken(String token);
    boolean existsByToken(String token);
}
