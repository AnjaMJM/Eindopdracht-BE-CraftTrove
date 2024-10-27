package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.DTOs.purchaseDTO.PurchaseInputDTO;
import com.crafter.crafttroveapi.DTOs.purchaseDTO.PurchaseOutputDTO;
import com.crafter.crafttroveapi.exceptions.RecordNotFoundException;
import com.crafter.crafttroveapi.exceptions.RequirementsNotMetException;
import com.crafter.crafttroveapi.mappers.PurchaseMapper;
import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.models.Purchase;
import com.crafter.crafttroveapi.models.Review;
import com.crafter.crafttroveapi.models.User;
import com.crafter.crafttroveapi.repositories.PurchaseRepository;
import com.crafter.crafttroveapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

@Service
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
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            purchase.setUser(user);
        } else {
            throw new RecordNotFoundException("User not found with username: " + username);
        }

        Date dateOfReview = new Date();
        purchase.setDate(dateOfReview);

        double totalPrice = 0.00;
        if (purchase.getProducts() != null) {
            for (Product product : purchase.getProducts()) {
                totalPrice += product.getPrice();
            }
        } else {
            throw new RequirementsNotMetException("Your basket is empty");
        }
        BigDecimal formattedTotalPrice = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP);
        purchase.setTotalPrice(formattedTotalPrice.doubleValue());

        Purchase savedPurchase = purchaseRepository.save(purchase);
        return purchaseMapper.purchaseToOutput(savedPurchase);
    }
}
