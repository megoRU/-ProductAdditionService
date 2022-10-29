package com.mego.product.addition.service.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String error;
    @JsonProperty("error_description")
    private String errorDescription;
}