package br.com.fiap.orderservice.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private int id;
    private String cardNumber;
    private String expiringDate;
    private String cardFlag;
}
