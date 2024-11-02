package com.crafter.crafttroveapi.DTOs.fileDTO;

import com.crafter.crafttroveapi.models.Designer;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUploadDTO {

    @NotNull
    private String name;
    @NotNull
    private byte[] fileData;
    @NotNull
    private String url;

    private String contentType;

    private long size;

    private Designer designer;
//    private Product product;
}
