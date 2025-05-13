package com.ouaskanas.commerce.dto.response;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Long productId;
    private String productName;
    private String productDescription;
    private String productImg;
    private Float productPrice;
    private int productStock;
    private String categoryName;
}
