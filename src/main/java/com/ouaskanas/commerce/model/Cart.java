package com.ouaskanas.commerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> product;
    private float price;

    public Float getPrice() {
        return product.stream()
                .map(Product::getProductPrice)
                .reduce(0f, Float::sum);
    }
}
