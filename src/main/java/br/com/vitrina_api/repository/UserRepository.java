package br.com.vitrina_api.repository;

import br.com.vitrina_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

        Optional<User> findByEmail(String email);

}
