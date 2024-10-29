package com.crafter.crafttroveapi.controllers;

import com.crafter.crafttroveapi.DTOs.designerDTO.DesignerInputDTO;
import com.crafter.crafttroveapi.DTOs.designerDTO.DesignerOutputDTO;
import com.crafter.crafttroveapi.helpers.validation.CreateGroup;
import com.crafter.crafttroveapi.helpers.validation.UpdateGroup;
import com.crafter.crafttroveapi.services.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/designers")
public class DesignerController {

    private final DesignerService designerService;
    private Authentication authentication;

    @Autowired
    public DesignerController(DesignerService designerService) {
        this.designerService = designerService;
    }

    private void setAuthentication(SecurityContext context) {
        this.authentication =context.getAuthentication();
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
    public ResponseEntity<DesignerOutputDTO> createNewDesigner(@RequestBody @Validated(CreateGroup.class) DesignerInputDTO newDesigner) {
        setAuthentication(SecurityContextHolder.getContext());
        DesignerOutputDTO createdDesigner = designerService.createNewDesigner(newDesigner);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(createdDesigner.getBrandName())
                .toUri();
        return ResponseEntity.created(location).body(createdDesigner);
    }

    @PutMapping("/{name}")
    public ResponseEntity<DesignerOutputDTO> updateDesigner(@PathVariable String name, @RequestBody @Validated(UpdateGroup.class) DesignerInputDTO updatedDesigner){
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
