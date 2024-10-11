package com.crafter.crafttroveapi.DTOs.designerDTO;

import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Getter
@Setter
public class DesignerInputDTO {

    @NotNull
    @UniqueElements
    private String designerName;

    @NotNull
    @UniqueElements
    private String brandName;

    private String shopDescription;

//    @OneToOne(mappedBy = "isDesigner")
//    private User user;

    private List<Long> productIdList;
}
