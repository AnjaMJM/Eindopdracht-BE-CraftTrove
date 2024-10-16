package com.crafter.crafttroveapi.controllers;

import com.crafter.crafttroveapi.DTOs.keywordDTO.KeywordOutputDTO;
import com.crafter.crafttroveapi.services.KeywordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/keywords")
public class KeywordController {

    private final KeywordService keywordService;

    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @GetMapping
    public ResponseEntity<List<KeywordOutputDTO>> getAllKeywords(){
        return ResponseEntity.ok(keywordService.getAllKeywords());
    }
}
