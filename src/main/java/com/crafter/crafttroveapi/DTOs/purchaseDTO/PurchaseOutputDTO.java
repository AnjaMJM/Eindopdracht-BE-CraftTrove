package com.crafter.crafttroveapi.DTOs.purchaseDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PurchaseOutputDTO {

    private Long id;
    private String username;
    private Date date;
    private double totalPrice;
    private List<String> products;
    private boolean isPayed;
}
