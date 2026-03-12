package br.com.vitrina_api.modules.store.repository;

import br.com.vitrina_api.modules.store.model.NumberContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NumberContactRepository extends JpaRepository<NumberContact, Long> {
    List<NumberContact> findByStorePublicId(UUID pulicId);
    Optional<NumberContact> findByIdAndStorePublicId(Long id, UUID publicId);
}
