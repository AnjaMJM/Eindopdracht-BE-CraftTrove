package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.DTOs.purchaseDTO.PurchaseInputDTO;
import com.crafter.crafttroveapi.DTOs.purchaseDTO.PurchaseOutputDTO;
import com.crafter.crafttroveapi.exceptions.RecordNotFoundException;
import com.crafter.crafttroveapi.exceptions.RequirementsNotMetException;
import com.crafter.crafttroveapi.mappers.PurchaseMapper;
import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.models.Purchase;
import com.crafter.crafttroveapi.models.User;
import com.crafter.crafttroveapi.repositories.PurchaseRepository;
import com.crafter.crafttroveapi.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final PurchaseMapper purchaseMapper;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, UserRepository userRepository, PurchaseMapper purchaseMapper) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.purchaseMapper = purchaseMapper;
    }

    public PurchaseOutputDTO createPurchase(PurchaseInputDTO newPurchase) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Purchase purchase = purchaseMapper.inputToPurchase(newPurchase);
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new RecordNotFoundException("User not found with username: " + username);
        }
        User user = optionalUser.get();
        purchase.setUser(user);
        if (purchase.getProducts().isEmpty()) {
            throw new RequirementsNotMetException("Your basket is empty");
        }

        double totalPrice = 0.00;
        for (Product product : purchase.getProducts()) {
            if(!product.getIsAvailable()){
                throw new RequirementsNotMetException("Product " + product.getTitle() + " is not available");
            }
            totalPrice += product.getPrice();
            BigDecimal formattedTotalPrice = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP);
            purchase.setTotalPrice(formattedTotalPrice.doubleValue());
        }

        Date dateOfReview = new Date();
        purchase.setDate(dateOfReview);

        Purchase savedPurchase = purchaseRepository.save(purchase);
        return purchaseMapper.purchaseToOutput(savedPurchase);
    }
}
