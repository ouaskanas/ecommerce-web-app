package com.ouaskanas.commerce.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDto {
    private String productName;
    private String productDescription;
    private String productImg;
    private Float productPrice;
    private int productStock;
    private String category;
}
