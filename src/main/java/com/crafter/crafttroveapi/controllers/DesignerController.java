package com.crafter.crafttroveapi.controllers;

import com.crafter.crafttroveapi.DTOs.designerDTO.DesignerInputDTO;
import com.crafter.crafttroveapi.DTOs.designerDTO.DesignerOutputDTO;
import com.crafter.crafttroveapi.exceptions.DuplicateRecordException;
import com.crafter.crafttroveapi.services.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public DesignerController(DesignerService designerService) {
        this.designerService = designerService;
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

        try {
            DesignerOutputDTO createdDesigner = designerService.createNewDesigner(newDesigner, logo);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{name}")
                    .buildAndExpand(createdDesigner.getBrandName())
                    .toUri();

            return ResponseEntity.created(location).body(createdDesigner);
        } catch (DuplicateRecordException ex) {
            throw new DuplicateRecordException("There already is a designer account assigned");
        }
    }

    @PatchMapping("/{name}")
    public ResponseEntity<DesignerOutputDTO> updateDesigner(@PathVariable String name, @RequestBody DesignerInputDTO updatedDesigner){
        DesignerOutputDTO update = designerService.updateDesigner(name, updatedDesigner);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteDesigner(@PathVariable String name) {

        designerService.deleteDesigner(name);
        return ResponseEntity.ok("Designer shop successfully deleted");
    }
}
