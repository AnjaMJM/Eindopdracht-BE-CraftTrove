package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.repositories.DesignerRepository;
import org.springframework.stereotype.Service;

@Service
public class DesignerService {

    private final DesignerRepository designerRepository;

    public DesignerService(DesignerRepository designerRepository) {
        this.designerRepository = designerRepository;
    }
}
