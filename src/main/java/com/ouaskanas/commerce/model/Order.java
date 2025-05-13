package com.ouaskanas.commerce.model;

import com.ouaskanas.commerce.model.enums.OrderState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oderId;
    @OneToOne
    private Cart cart;
    private LocalDate orderDate;
    private OrderState orderState;
}
