package com.crafter.crafttroveapi.controllers;

import com.crafter.crafttroveapi.DTOs.designerDTO.DesignerInputDTO;
import com.crafter.crafttroveapi.DTOs.designerDTO.DesignerOutputDTO;
import com.crafter.crafttroveapi.services.DesignerService;
import com.crafter.crafttroveapi.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/designers")
public class DesignerController {

    private final DesignerService designerService;
    private final FileService fileService;

    @Autowired
    public DesignerController(DesignerService designerService, FileService fileService) {
        this.designerService = designerService;
        this.fileService = fileService;
    }

    private void setAuthentication(SecurityContext context) {
        Authentication authentication = context.getAuthentication();
    }

    @GetMapping()
    public ResponseEntity<List<DesignerOutputDTO>> getAllDesigners(){
        return ResponseEntity.ok(designerService.getAllDesigners());
    }

    @GetMapping("/{name}")
    public ResponseEntity<DesignerOutputDTO> getDesignerByBrandname(@PathVariable String name) {
        return ResponseEntity.ok(designerService.getDesignersByBrandname(name));
    }

    @PostMapping()
    public ResponseEntity<DesignerOutputDTO> createNewDesigner(@RequestPart("designer") DesignerInputDTO newDesigner, @RequestPart("logo") MultipartFile logo) throws IOException {
        setAuthentication(SecurityContextHolder.getContext());

        DesignerOutputDTO createdDesigner = designerService.createNewDesigner(newDesigner, logo);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(createdDesigner.getBrandName())
                .toUri();

        return ResponseEntity.created(location).body(createdDesigner);
    }

    @PutMapping("/{name}")
    public ResponseEntity<DesignerOutputDTO> updateDesigner(@PathVariable String name, @RequestBody DesignerInputDTO updatedDesigner){
        setAuthentication(SecurityContextHolder.getContext());
        DesignerOutputDTO update = designerService.updateDesigner(name, updatedDesigner);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteDesigner(@PathVariable String name, @PathVariable Long id) {
        setAuthentication(SecurityContextHolder.getContext());
        designerService.deleteDesigner(name);
        return ResponseEntity.ok("Designer successfully deleted");
    }
}
