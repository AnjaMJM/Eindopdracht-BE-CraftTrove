package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.DTOs.designerDTO.DesignerInputDTO;
import com.crafter.crafttroveapi.DTOs.designerDTO.DesignerOutputDTO;
import com.crafter.crafttroveapi.DTOs.productDTO.ProductInputDTO;
import com.crafter.crafttroveapi.DTOs.productDTO.ProductOutputDTO;
import com.crafter.crafttroveapi.annotations.CheckAvailability;
import com.crafter.crafttroveapi.exceptions.DuplicateRecordException;
import com.crafter.crafttroveapi.exceptions.RecordNotFoundException;
import com.crafter.crafttroveapi.helpers.CheckType;
import com.crafter.crafttroveapi.mappers.DesignerMapper;
import com.crafter.crafttroveapi.models.Category;
import com.crafter.crafttroveapi.models.Designer;
import com.crafter.crafttroveapi.models.Keyword;
import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.repositories.DesignerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesignerService {

    private final DesignerRepository designerRepository;
    private final DesignerMapper designerMapper;

    public DesignerService(DesignerRepository designerRepository, DesignerMapper designerMapper) {
        this.designerRepository = designerRepository;
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

    public DesignerOutputDTO getDesignerById(Long id) {
        Designer designer = designerRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Designer not found"));

        return designerMapper.designerToOutput(designer);
    }

    public DesignerOutputDTO getDesignersByBrandname(String name) {
        Designer designer = designerRepository.findDesignerByBrandNameIgnoreCase(name)
                .orElseThrow(() -> new RecordNotFoundException("Designer not found"));

        return designerMapper.designerToOutput(designer);
    }

    public DesignerOutputDTO createNewDesigner(DesignerInputDTO newDesigner ) {//Long userId
        if (designerRepository.existsByBrandNameIgnoreCase(newDesigner.getBrandName())) {
            throw new DuplicateRecordException("This brandname is already in use");
        }
        // Voorwaarde toevoegen: user.isDesigner == true
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
