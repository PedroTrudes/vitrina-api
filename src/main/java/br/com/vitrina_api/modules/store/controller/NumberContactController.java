package br.com.vitrina_api.modules.store.controller;

import br.com.vitrina_api.modules.store.dto.UpdateNumberContactDTO;
import br.com.vitrina_api.modules.store.model.NumberContact;
import br.com.vitrina_api.modules.store.service.NumberContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class NumberContactController {

    private final NumberContactService numberContactService;

    @PostMapping("/{publicId}/contacts")
    public ResponseEntity<NumberContact> create(@PathVariable UUID publicId,
                                                @RequestBody NumberContact contact){
        NumberContact saveContact = numberContactService.create(publicId, contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveContact);
    }

    @GetMapping("/{publicId}/contacts")
    public ResponseEntity<List<NumberContact>> getAllContactByStore(@PathVariable UUID publicId){
        return ResponseEntity.ok(numberContactService.getAll(publicId));
    }

    @DeleteMapping("/{publicId}/contacts/{id}")
    public ResponseEntity<Void> deleteContactByStore(@PathVariable UUID publicId, @PathVariable Long id){
        numberContactService.delete(publicId, id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{publicId}/contacts/{id}")
    public ResponseEntity<NumberContact> updateContactByStore(@PathVariable UUID publicId, @PathVariable Long id, @RequestBody UpdateNumberContactDTO dto){
        NumberContact updateContact = numberContactService.update(id, publicId, dto);
        return ResponseEntity.ok(updateContact);
    }

}
