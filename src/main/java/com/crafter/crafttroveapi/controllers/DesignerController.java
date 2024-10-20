package com.crafter.crafttroveapi.controllers;

import com.crafter.crafttroveapi.DTOs.designerDTO.DesignerInputDTO;
import com.crafter.crafttroveapi.DTOs.designerDTO.DesignerOutputDTO;
import com.crafter.crafttroveapi.helpers.validation.CreateGroup;
import com.crafter.crafttroveapi.helpers.validation.UpdateGroup;
import com.crafter.crafttroveapi.services.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class DesignerController {

    private final DesignerService designerService;

    @Autowired
    public DesignerController(DesignerService designerService) {
        this.designerService = designerService;
    }

    @GetMapping
    public ResponseEntity<List<DesignerOutputDTO>> getAllDesigners(){
        return ResponseEntity.ok(designerService.getAllDesigners());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DesignerOutputDTO> getDesignerById(@PathVariable Long id) {
        return ResponseEntity.ok(designerService.getDesignerById(id));
    }

    @GetMapping("/{name}")
    public ResponseEntity<DesignerOutputDTO> getDesignerByBrandname(@PathVariable String name) {
        return ResponseEntity.ok(designerService.getDesignersByBrandname(name));
    }

    @PostMapping
    public ResponseEntity<DesignerOutputDTO> createNewDesigner(@RequestBody @Validated(CreateGroup.class) DesignerInputDTO newDesigner) {
        DesignerOutputDTO createdDesigner = designerService.createNewDesigner(newDesigner);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdDesigner.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdDesigner);
    }

    @PutMapping("/{name}")
    public ResponseEntity<DesignerOutputDTO> updateDesigner(@PathVariable String name, @RequestBody @Validated(UpdateGroup.class) DesignerInputDTO updatedDesigner){
        DesignerOutputDTO update = designerService.updateDesigner(name, updatedDesigner);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteDesigner(@PathVariable String name) {
        designerService.deleteDesigner(name);
        return ResponseEntity.ok("Designer successfully deleted");
    }
}
