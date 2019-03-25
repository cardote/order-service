package br.com.fiap.orderservice.model;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private int idOrder;
    private String description;
    private BigDecimal value;
    private int amount;

}
