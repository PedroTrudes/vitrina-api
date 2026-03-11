package br.com.vitrina_api.modules.store.repository;

import br.com.vitrina_api.modules.store.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByName(String name);

    Optional<Store> findByPublicId(UUID publicId);
}
