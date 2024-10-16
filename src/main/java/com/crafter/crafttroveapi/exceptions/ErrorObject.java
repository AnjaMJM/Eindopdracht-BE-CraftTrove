package com.crafter.crafttroveapi.exceptions;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorObject {
    private Integer statusCode;
    private String message;
    private List<String> messages;
    private Date timestamp;

}
