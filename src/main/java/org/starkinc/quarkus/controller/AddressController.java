package org.starkinc.quarkus.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.starkinc.quarkus.model.dto.AddressDTO;
import org.starkinc.quarkus.service.AddressService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<Object> getAddress() {
        log.debug("Invoking faker service");
        AddressDTO address = addressService.getRandomAddress();
        return ResponseEntity.ok(address);
    }

    @GetMapping("/save")
    public ResponseEntity<Object> saveAddress() {
        addressService.saveAddress();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fetch")
    public ResponseEntity<Object> fetchRandomAddress() {
        List<AddressDTO> addressDTOS = addressService.fetchRandomAddress();
        return ResponseEntity.ok(addressDTOS);
    }
}
