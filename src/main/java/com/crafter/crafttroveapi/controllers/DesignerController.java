package com.crafter.crafttroveapi.controllers;

import com.crafter.crafttroveapi.DTOs.designerDTO.DesignerOutputDTO;
import com.crafter.crafttroveapi.DTOs.productDTO.ProductInputDTO;
import com.crafter.crafttroveapi.DTOs.productDTO.ProductOutputDTO;
import com.crafter.crafttroveapi.DTOs.validation.CreateGroup;
import com.crafter.crafttroveapi.DTOs.validation.UpdateGroup;
import com.crafter.crafttroveapi.services.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class DesignerController {

    private final DesignerService designerService;

    @Autowired
    public DesignerController(DesignerService designerService) {
        this.designerService = designerService;
    }

    @GetMapping
    public ResponseEntity<List<DesignerOutputDTO>> getAllDesigners(){


        return ResponseEntity.ok(designerService.getAllDesigners());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOutputDTO> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductOutputDTO> createNewProduct(@RequestBody @Validated(CreateGroup.class) ProductInputDTO newProduct) {
        ProductOutputDTO createdProduct = productService.createNewProduct(newProduct);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProduct.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductOutputDTO> updateProduct(@PathVariable Long id, @RequestBody @Validated(UpdateGroup.class) ProductInputDTO updatedProduct){
        ProductOutputDTO update = productService.updateProduct(id, updatedProduct);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product successfully deleted");
    }
}
