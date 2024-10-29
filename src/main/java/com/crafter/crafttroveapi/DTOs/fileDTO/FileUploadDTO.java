package com.crafter.crafttroveapi.DTOs.fileDTO;

import com.crafter.crafttroveapi.models.Designer;
import com.crafter.crafttroveapi.models.Product;;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FileUploadDTO {

    private String name;

    private MultipartFile fileData;

    private String contentType;

    private long size;

    private Designer designer;

    private Product product;
}
