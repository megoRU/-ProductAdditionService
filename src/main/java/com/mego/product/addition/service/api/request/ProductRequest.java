package com.mego.product.addition.service.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    public String name;
    public String description;
    public int kcal;

    @JsonProperty("list_id")
    public String listId;
}