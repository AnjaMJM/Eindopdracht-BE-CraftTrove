package com.crafter.crafttroveapi.DTOs.fileDTO;

import com.crafter.crafttroveapi.models.Designer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileLogoOutputDTO {

    private Long id;
    private String name;
    private String url;
    private String contentType;
    private Designer designer;
}
