package com.crafter.crafttroveapi.controllers;

import com.crafter.crafttroveapi.DTOs.productDTO.ProductInputDTO;
import com.crafter.crafttroveapi.DTOs.productDTO.ProductOutputDTO;
import com.crafter.crafttroveapi.DTOs.productDTO.ProductPatchInputDTO;
import com.crafter.crafttroveapi.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.management.DescriptorKey;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private void setAuthentication(SecurityContext context) {
        Authentication authentication = context.getAuthentication();
    }

    @GetMapping
    public ResponseEntity<List<ProductOutputDTO>> getAllProducts(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "keyword", required = false) String keyword
    ){
        if (category != null) {
            return ResponseEntity.ok(productService.getProductsByCategory(category));
        }
        if (keyword != null) {
            return ResponseEntity.ok(productService.getProductsByKeywords(keyword));
        }
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOutputDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("/designer")
    public ResponseEntity<ProductOutputDTO> createNewProduct(@Valid @RequestBody ProductInputDTO newProduct) {
        ProductOutputDTO createdProduct = productService.createNewProduct(newProduct);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProduct.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdProduct);
    }

    @PatchMapping("/designer/{id}")
    public ResponseEntity<ProductOutputDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductPatchInputDTO updatedProduct){
        ProductOutputDTO update = productService.updateProduct(id, updatedProduct);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/designer/{id}")
    public ResponseEntity<String> deleteProductByDesigner(@PathVariable Long id) {
        productService.deleteProductByDesigner(id);
        return ResponseEntity.ok("Product successfully deleted");
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteProductByAdmin(@PathVariable Long id) {
        productService.deleteProductByAdmin(id);
        return ResponseEntity.ok("Product successfully deleted");
    }
}
