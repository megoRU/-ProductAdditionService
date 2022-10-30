package com.mego.product.addition.service.api.response;

import com.mego.product.addition.service.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ListOfProducts {

   private List<Product> products;
   private long totalAmountKcal;
}
