package com.crafter.crafttroveapi.controllers;

import com.crafter.crafttroveapi.DTOs.purchaseDTO.PurchaseInputDTO;
import com.crafter.crafttroveapi.DTOs.purchaseDTO.PurchaseOutputDTO;
import com.crafter.crafttroveapi.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseOutputDTO>  createNewPurchase( @RequestBody PurchaseInputDTO newPurchase) {
        PurchaseOutputDTO createdPurchase = purchaseService.createPurchase(newPurchase);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPurchase.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdPurchase);
    }
}
