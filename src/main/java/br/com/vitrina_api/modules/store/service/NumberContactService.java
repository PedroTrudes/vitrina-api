package br.com.vitrina_api.modules.store.service;

import br.com.vitrina_api.modules.store.dto.UpdateNumberContactDTO;
import br.com.vitrina_api.modules.store.model.NumberContact;
import br.com.vitrina_api.modules.store.model.Store;
import br.com.vitrina_api.modules.store.repository.NumberContactRepository;
import br.com.vitrina_api.modules.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NumberContactService {

    private final NumberContactRepository numberContactRepository;
    private final StoreRepository storeRepository;

    public NumberContact create(UUID pulicId, NumberContact contact){
        Store store = storeRepository.findByPublicId(pulicId).orElseThrow(() -> new RuntimeException("Loja não localizada"));

        contact.setStore(store);
        contact.setActive(true);

        return numberContactRepository.save(contact);
    }

    public List<NumberContact> getAll(UUID publicId){
        return numberContactRepository.findByStorePublicId(publicId);
    }

    public void delete(UUID publicId, Long id){
        Store store = storeRepository.findByPublicId(publicId).orElseThrow(() -> new RuntimeException("Loja não localizada"));

        NumberContact contact = numberContactRepository.findById(id).orElseThrow(() -> new RuntimeException("Contato não localizado"));

        if(!contact.getStore().getId().equals(store.getId())){
            throw new RuntimeException("Este contato não existe em loja");
        }

        numberContactRepository.delete(contact);
    }

    public NumberContact update(Long id, UUID publicId, UpdateNumberContactDTO dto){
        NumberContact contact = numberContactRepository.findByIdAndStorePublicId(id, publicId).orElseThrow(() -> new RuntimeException("Contato não localizado na loja"));
        contact.updateFrom(dto);
        return numberContactRepository.save(contact);
    }
}
