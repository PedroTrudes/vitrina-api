package br.com.vitrina_api.modules.store.service;

import br.com.vitrina_api.modules.store.model.Store;
import br.com.vitrina_api.modules.store.repository.StoreRepository;
import br.com.vitrina_api.modules.user.model.User;
import br.com.vitrina_api.modules.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    public StoreService(StoreRepository storeRepository, UserRepository userRepository) {

        this.storeRepository = storeRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Store createStore(Store store, UUID publicId){
        var storeExists = storeRepository.findByName(store.getName());

        if(storeExists.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Esta loja já existe");
        }
        Store savedStore = storeRepository.save(store);

        User owner = userRepository.findByPublicId(publicId).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        owner.setStore(savedStore);
        userRepository.save(owner);

        return savedStore;

    }

    public List<Store> findAll(){
        return storeRepository.findAll();
    }


    public Store findByPublicId(UUID publicId){
        return storeRepository.findByPublicId(publicId).orElseThrow(() -> new RuntimeException("Loja não encontrada"));
    }
}
