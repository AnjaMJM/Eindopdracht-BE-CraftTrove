package com.crafter.crafttroveapi.DTOs.purchaseDTO;

import com.crafter.crafttroveapi.models.Product;
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

    @NotNull
    private Double totalPrice;

    private List<Long> products;
}
