package br.com.fiap.orderservice.model;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private int id;
    private String email;
    private String fullName;
    private String shippingAddress;
    private Product[] products;
    private Payment payment;
    private String date;
    private String status;
    private BigDecimal total;
}
