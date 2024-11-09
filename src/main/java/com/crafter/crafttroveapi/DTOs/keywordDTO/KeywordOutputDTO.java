package com.crafter.crafttroveapi.DTOs.keywordDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KeywordOutputDTO {

    private String name;
    private List<Long> productIdList;
}
