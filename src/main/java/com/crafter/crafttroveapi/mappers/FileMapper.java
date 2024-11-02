package com.crafter.crafttroveapi.mappers;

import com.crafter.crafttroveapi.DTOs.fileDTO.FileLogoOutputDTO;
import com.crafter.crafttroveapi.DTOs.fileDTO.FileUploadDTO;
import com.crafter.crafttroveapi.models.File;

import java.util.Base64;

public class FileMapper {

    public File uploadToFile(FileUploadDTO uploadDTO){
        File file = new File();
        file.setName(uploadDTO.getName());
        file.setFileData(uploadDTO.getFileData());
        file.setUrl(uploadDTO.getUrl());
        file.setContentType(uploadDTO.getContentType());
        file.setSize(uploadDTO.getSize());
        file.setDesigner(uploadDTO.getDesigner());

        return file;
    }

    public FileLogoOutputDTO fileToLogo (File logoFile){
        FileLogoOutputDTO logoOutputDTO = new FileLogoOutputDTO();
        logoOutputDTO.setId(logoFile.getId());
        logoOutputDTO.setName(logoFile.getName());
        logoOutputDTO.setUrl(logoFile.getUrl());
        logoOutputDTO.setContentType(logoFile.getContentType());
        logoOutputDTO.setDesigner(logoFile.getDesigner());

        return logoOutputDTO;
    }
}
