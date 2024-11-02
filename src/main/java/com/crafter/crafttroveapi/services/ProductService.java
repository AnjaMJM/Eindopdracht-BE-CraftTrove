package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.DTOs.productDTO.ProductInputDTO;
import com.crafter.crafttroveapi.DTOs.productDTO.ProductOutputDTO;
import com.crafter.crafttroveapi.DTOs.productDTO.ProductPatchInputDTO;
import com.crafter.crafttroveapi.annotations.CheckAvailability;
import com.crafter.crafttroveapi.exceptions.ConflictWithResourceStateException;
import com.crafter.crafttroveapi.exceptions.DuplicateRecordException;
import com.crafter.crafttroveapi.exceptions.FailToAuthenticateException;
import com.crafter.crafttroveapi.exceptions.RecordNotFoundException;
import com.crafter.crafttroveapi.helpers.CheckType;
import com.crafter.crafttroveapi.helpers.PatchHelper;
import com.crafter.crafttroveapi.mappers.ProductMapper;
import com.crafter.crafttroveapi.models.*;
import com.crafter.crafttroveapi.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final KeywordRepository keywordRepository;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, KeywordRepository keywordRepository, ProductMapper productMapper, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.keywordRepository = keywordRepository;
        this.productMapper = productMapper;
        this.userRepository = userRepository;
    }

    public List<ProductOutputDTO> getAllProducts() {
        List<Product> products = productRepository.findAllAvailableProducts();
        List<ProductOutputDTO> dtos = new ArrayList<>();

        for (Product product : products) {
            dtos.add(productMapper.productToOutput(product));
        }
        return dtos;
    }

    @CheckAvailability
    public ProductOutputDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RecordNotFoundException("Product not found"));

        return productMapper.productToOutput(product);
    }

    @CheckAvailability(type = CheckType.CATEGORY)
    public List<ProductOutputDTO> getProductsByCategory(String categoryName) {
        Category category = categoryRepository.findByNameIgnoreCase(categoryName)
                .orElseThrow(() -> new RecordNotFoundException("Category with categoryName " + categoryName + " not found"));

        return productMapper.ListProductToOutput(category.getProducts());
    }

    @CheckAvailability(type = CheckType.KEYWORD)
    public List<ProductOutputDTO> getProductsByKeywords(String keywordName) {
        Keyword keyword = keywordRepository.findByNameIgnoreCase(keywordName)
                .orElseThrow(() -> new RecordNotFoundException("Keyword " + keywordName + " is not found"));

        return productMapper.ListProductToOutput(keyword.getProducts());
    }

    @Transactional
    public ProductOutputDTO createNewProduct(ProductInputDTO newProduct) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.isDesigner()) {
                Designer designer = user.getDesigner();
                newProduct.setDesigner(designer);
            } else {
                throw new RecordNotFoundException("There is no designer account for user " + username);
            }
        } else {
            throw new RecordNotFoundException("User not found");
        }
        if (productRepository.existsByTitleIgnoreCase(newProduct.getTitle())) {
            throw new DuplicateRecordException("A product with this name already exists");
        }
        Product p = productRepository.save(productMapper.inputToProduct(newProduct));
        return productMapper.productToOutput(p);
    }

    @Transactional
    public ProductOutputDTO updateProduct(Long id, ProductPatchInputDTO updatedProduct) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new RecordNotFoundException("User not found");
        }
        User user = optionalUser.get();
        if (!user.isDesigner()) {
            throw new RecordNotFoundException("There is no designer account for user " + username);
        }

        Designer designer = user.getDesigner();
        Optional<Product> product = productRepository.findByIdAndDesigner(id, designer);
        if (product.isEmpty()) {
            throw new RecordNotFoundException("This product does not exist in your shop");
        }

        if (productRepository.existsByTitleIgnoreCase(updatedProduct.getTitle())) {
            throw new DuplicateRecordException("A product with this name already exists");
        }

        Product existingProduct = product.get();
        BeanUtils.copyProperties(updatedProduct, existingProduct, PatchHelper.getNullPropertyNames(updatedProduct));

        Product savedProduct = productRepository.save(existingProduct);

        return productMapper.productToOutput(savedProduct);
    }

    @Transactional
    public void deleteProductByDesigner(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new RecordNotFoundException("User not found");
        }
        User user = optionalUser.get();

        if (!user.isDesigner()) {
            throw new RecordNotFoundException("There is no designer account for user " + username);
        }
        Designer designer = user.getDesigner();

        Optional<Product> product = productRepository.findByIdAndDesigner(id, designer);
        if (product.isEmpty()) {
            throw new RecordNotFoundException("This product does not exist in your shop or is already set to unavailable");
        }
        Product existingProduct = product.get();
        if(!existingProduct.getIsAvailable()) {
            throw new ConflictWithResourceStateException("This product is already unavailable");
        }

        existingProduct.setIsAvailable(false);
    }

    @Transactional
    public void deleteProductByAdmin(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        if (!isAdmin) {
            throw new FailToAuthenticateException("Authentication for this action failed");
        }

        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new RecordNotFoundException("This product does not exist");
        }
        Product existingProduct = product.get();
        if(!existingProduct.getIsAvailable()) {
            throw new ConflictWithResourceStateException("This product is already unavailable");
        }
        existingProduct.setIsAvailable(false);
    }
}
