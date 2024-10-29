package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.DTOs.keywordDTO.KeywordOutputDTO;
import com.crafter.crafttroveapi.models.Keyword;
import com.crafter.crafttroveapi.repositories.KeywordRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.crafter.crafttroveapi.mappers.KeywordMapper.KeywordToOutput;

@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }


    public List<KeywordOutputDTO> getAllKeywords() {
        List<Keyword> keywords = keywordRepository.findAll();
        List<KeywordOutputDTO> dtos = new ArrayList<>();

        for (Keyword keyword : keywords) {
            KeywordOutputDTO keywordOutputDTO = KeywordToOutput(keyword);
            if (!keywordOutputDTO.getProductIdList().isEmpty()) {
                dtos.add(keywordOutputDTO);
            }
        }
        return dtos;
    }
}
