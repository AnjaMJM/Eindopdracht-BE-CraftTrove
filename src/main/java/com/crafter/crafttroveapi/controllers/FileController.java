package com.crafter.crafttroveapi.controllers;

import com.crafter.crafttroveapi.DTOs.designerDTO.DesignerOutputDTO;
import com.crafter.crafttroveapi.DTOs.fileDTO.FileLogoOutputDTO;
import com.crafter.crafttroveapi.exceptions.RecordNotFoundException;
import com.crafter.crafttroveapi.models.File;
import com.crafter.crafttroveapi.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/logo/{uniqueUrl}")
    public ResponseEntity<ByteArrayResource> getLogoByUniqueUrl(@PathVariable String uniqueUrl) {
        File logo = fileService.getPhotoByUniqueUrl(uniqueUrl)
                .orElseThrow(() -> new RecordNotFoundException("Logo not found"));

        ByteArrayResource resource = new ByteArrayResource(logo.getFileData());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(logo.getContentType()))
                .contentLength(logo.getSize())
                .body(resource);
    }
}
