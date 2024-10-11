package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.DTOs.productDTO.ProductOutputDTO;
import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


import static com.crafter.crafttroveapi.mappers.ProductMapper.ProductToOutput;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductOutputDTO> getAllProducts(){
        List<Product> products = productRepository.findAll();
        List<ProductOutputDTO> dtos = new ArrayList<>();

        for(Product product:products){
            dtos.add(ProductToOutput(product));
        }
        return dtos;
    }

}
