package com.crafter.crafttroveapi.DTOs.purchaseDTO;

import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.models.User;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PurchaseInputDTO {

    @NotNull
    private Date date;
    private User user;

    @NotNull
    private double totalPrice;

    private List<Long> products;

    private boolean payed;
}
