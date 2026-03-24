package br.com.vitrina_api.modules.store.controller;

import br.com.vitrina_api.modules.auth.model.CustomUserDetails;
import br.com.vitrina_api.modules.store.dto.StoreDTO;
import br.com.vitrina_api.modules.store.model.Store;
import br.com.vitrina_api.modules.store.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody StoreDTO storeDTO, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        UUID publicId = customUserDetails.getPublicId();

        Store store = new Store();
        store.setName(storeDTO.getName());
        store.setEmail(storeDTO.getEmail());
        store.setSlug(storeDTO.getSlug());;

        storeService.createStore(store, publicId);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping()
    public List<Store> getAll(){
        return storeService.findAll();
    }

    @GetMapping("/{publicId}")
    public ResponseEntity<Store> getById(@PathVariable UUID publicId){
        Store store = storeService.findByPublicId(publicId);
        return ResponseEntity.ok(store);
    }


}
