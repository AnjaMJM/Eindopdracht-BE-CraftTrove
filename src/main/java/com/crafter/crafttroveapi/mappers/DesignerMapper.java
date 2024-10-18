package com.crafter.crafttroveapi.mappers;

import com.crafter.crafttroveapi.DTOs.designerDTO.DesignerInputDTO;
import com.crafter.crafttroveapi.DTOs.designerDTO.DesignerOutputDTO;
import com.crafter.crafttroveapi.models.Designer;
import com.crafter.crafttroveapi.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DesignerMapper {

    public DesignerOutputDTO DesignerToOutput(Designer designer){
        DesignerOutputDTO dto = new DesignerOutputDTO();
        dto.setUsername(designer.getUsername());
        dto.setBrandName(designer.getBrandName());
        dto.setLogo(designer.getBrandLogo());
        dto.setBrandDescription(designer.getBrandDescription());
        if (designer.getProducts() != null) {
            List<Long> productIds = new ArrayList<>();
            for (Product product : designer.getProducts()) {
                productIds.add(product.getId());
            }
            dto.setProductIdList(productIds);
        }
        return dto;
    }

    public Designer InputToDesigner(DesignerInputDTO inputDTO) {
        Designer designer = new Designer();
        designer.setBrandName(inputDTO.getBrandName());
        designer.setBrandLogo(inputDTO.getLogo());
        designer.setBrandDescription(inputDTO.getBrandDescription());
        return designer;
    }
}
