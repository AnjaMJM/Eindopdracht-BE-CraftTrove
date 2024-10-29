package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.DTOs.designerDTO.DesignerInputDTO;
import com.crafter.crafttroveapi.DTOs.designerDTO.DesignerOutputDTO;
import com.crafter.crafttroveapi.annotations.CheckAvailability;
import com.crafter.crafttroveapi.exceptions.DuplicateRecordException;
import com.crafter.crafttroveapi.exceptions.RecordNotFoundException;
import com.crafter.crafttroveapi.helpers.RoleEnum;
import com.crafter.crafttroveapi.mappers.DesignerMapper;
import com.crafter.crafttroveapi.models.*;
import com.crafter.crafttroveapi.repositories.DesignerRepository;
import com.crafter.crafttroveapi.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DesignerService {

    private final DesignerRepository designerRepository;
    private final UserRepository userRepository;
    private final DesignerMapper designerMapper;

    public DesignerService(DesignerRepository designerRepository, UserRepository userRepository, DesignerMapper designerMapper) {
        this.designerRepository = designerRepository;
        this.userRepository = userRepository;
        this.designerMapper = designerMapper;
    }

    public List<DesignerOutputDTO> getAllDesigners() {
        List<Designer> designers = designerRepository.findAll();
        List<DesignerOutputDTO> dtos = new ArrayList<>();

        for (Designer designer : designers) {
            dtos.add(designerMapper.designerToOutput(designer));
        }
        return dtos;
    }

    public DesignerOutputDTO getDesignersByBrandname(String name) {
        Designer designer = designerRepository.findDesignerByBrandNameIgnoreCase(name)
                .orElseThrow(() -> new RecordNotFoundException("Designer not found"));

        return designerMapper.designerToOutput(designer);
    }

    public DesignerOutputDTO createNewDesigner(DesignerInputDTO newDesigner ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setDesigner(true);
            Set<Role> roleSet = user.getRoles();
            Role designerRole = new Role();
            designerRole.setName("ROLE_DESIGNER");
            roleSet.add(designerRole);
            user.setRoles(roleSet);
        } else {
            throw new RecordNotFoundException("User not found with username: " + username);
        }

        if (designerRepository.existsByBrandNameIgnoreCase(newDesigner.getBrandName())) {
            throw new DuplicateRecordException("This brandname is already in use");
        }

        Designer designer = designerRepository.save(designerMapper.inputToDesigner(newDesigner));
        return designerMapper.designerToOutput(designer);
    }

    public DesignerOutputDTO updateDesigner(String name, DesignerInputDTO updatedDesigner) {

        Optional<Designer> designer = designerRepository.findDesignerByBrandNameIgnoreCase(name);
        if (designer.isPresent()) {
            Designer existingDesigner = designer.get();

            if (updatedDesigner.getBrandName() != null) {
                if (designerRepository.existsByBrandNameIgnoreCase(updatedDesigner.getBrandName())) {
                    throw new DuplicateRecordException("This brandname is already in use");
                } else {
                    existingDesigner.setBrandName(updatedDesigner.getBrandName());
                }
            }
            if (updatedDesigner.getBrandDescription() != null) {
                existingDesigner.setBrandDescription(updatedDesigner.getBrandDescription());
            }
            if (updatedDesigner.getLogo() != null) {
                existingDesigner.setBrandLogo(updatedDesigner.getLogo());
            }

            Designer savedDesigner = designerRepository.save(existingDesigner);
            return designerMapper.designerToOutput(savedDesigner);
        } else {
            throw new RecordNotFoundException("Designer not found");
        }
    }

    @Transactional
    @CheckAvailability
    public void deleteDesigner(String name) {
        Optional<Designer> optionalDesigner = designerRepository.findDesignerByBrandNameIgnoreCase(name);
        if (optionalDesigner.isPresent()) {
            Designer designer = optionalDesigner.get();
            List<Product> products = optionalDesigner.get().getProducts();
            for(Product product:products){
                product.setIsAvailable(false);
            }
            designerRepository.delete(designer);
        } else {
            throw new RecordNotFoundException( "Brand " + name + " is not found");
        }
    }
}
