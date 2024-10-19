package com.crafter.crafttroveapi.DTOs.purchaseDTO;

import com.crafter.crafttroveapi.DTOs.productDTO.ProductOutputDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PurchaseOutputDTO {

    private Long id;
    private Date date;
    private Double totalPrice;
    private List<String> products;
}
