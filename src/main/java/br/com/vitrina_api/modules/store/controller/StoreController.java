package br.com.vitrina_api.modules.store.controller;

import br.com.vitrina_api.modules.store.model.Store;
import br.com.vitrina_api.modules.store.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> create(@RequestBody Store store){
        storeService.createStore(store);
        return ResponseEntity.status(HttpStatus.CREATED).body("Store registrada com sucesso");

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
