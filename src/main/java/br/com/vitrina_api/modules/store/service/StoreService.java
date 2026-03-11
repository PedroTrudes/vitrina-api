package br.com.vitrina_api.modules.store.service;

import br.com.vitrina_api.modules.store.model.Store;
import br.com.vitrina_api.modules.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store createStore(Store store){
        var storeExists = storeRepository.findByName(store.getName());

        if(storeExists.isPresent()){
            throw new RuntimeException("Esta loja já existe");
        }
        return storeRepository.save(store);
    }

    public List<Store> findAll(){
        return storeRepository.findAll();
    }


    public Store findByPublicId(UUID publicId){
        return storeRepository.findByPublicId(publicId).orElseThrow(() -> new RuntimeException("Loja não encontrada"));
    }
}
