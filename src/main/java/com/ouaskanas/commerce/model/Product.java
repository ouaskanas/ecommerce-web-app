package com.ouaskanas.commerce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    @Column(length = 500)
    private String productDescription;
    @Column(length = 500)
    private String productImg;
    private Float productPrice;
    private int productStock;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Category category;
}
