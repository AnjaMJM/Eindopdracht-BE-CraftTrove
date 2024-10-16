package com.crafter.crafttroveapi.DTOs.designerDTO;

import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Getter
@Setter
public class DesignerInputDTO {

    @NotEmpty(message = "What is your name? This may be the same as your username or you can choose something different if you prefer not to have your user activities linked to your shop")
    private String designerName;

    @NotEmpty(message = "Your brand needs a name too.")
    private String brandName;

    private String shopDescription;

//    @OneToOne(mappedBy = "isDesigner")
//    private User user;

    private List<Long> productIdList;
}
