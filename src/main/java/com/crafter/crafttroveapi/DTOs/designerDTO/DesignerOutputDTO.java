package com.crafter.crafttroveapi.DTOs.designerDTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Getter
@Setter
public class DesignerOutputDTO {

    private String username;
    private String brandName;
    private String logoUrl;
    private String brandDescription;
    private List<Long> productIdList;

    public void setLogoUrl(String uniqueUrl) {
        this.logoUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/logo/")
                .path(uniqueUrl)
                .toUriString();
    }
}
