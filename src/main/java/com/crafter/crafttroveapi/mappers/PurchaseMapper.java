package com.crafter.crafttroveapi.mappers;

import com.crafter.crafttroveapi.DTOs.purchaseDTO.PurchaseInputDTO;
import com.crafter.crafttroveapi.DTOs.purchaseDTO.PurchaseOutputDTO;
import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.models.Purchase;
import com.crafter.crafttroveapi.models.User;
import com.crafter.crafttroveapi.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseMapper {

    private final ProductRepository productRepository;

    public PurchaseMapper(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public PurchaseOutputDTO purchaseToOutput(Purchase purchase) {
        PurchaseOutputDTO dto = new PurchaseOutputDTO();
        User user = purchase.getUser();
        dto.setId(purchase.getId());
        dto.setUsername(user.getUsername());
        dto.setDate(purchase.getDate());
        dto.setTotalPrice(purchase.getTotalPrice());
        if (purchase.getProducts() != null) {
            List<String> productList = new ArrayList<>();
            for (Product product : purchase.getProducts()) {
                productList.add(product.getTitle());
            }
            dto.setProducts(productList);
        }
        dto.setPayed(purchase.isPayed());
        return dto;
    }

    public Purchase inputToPurchase(PurchaseInputDTO inputDTO) {
        Purchase purchase = new Purchase();
        purchase.setDate(inputDTO.getDate());
        purchase.setTotalPrice(inputDTO.getTotalPrice());
        purchase.setUser(inputDTO.getUser());
        if (inputDTO.getProducts() != null){
            List<Product> products = productRepository.findByIdIn(inputDTO.getProducts());
            purchase.setProducts(products);
        }
        purchase.setPayed(inputDTO.isPayed());
        return purchase;
    }
}
