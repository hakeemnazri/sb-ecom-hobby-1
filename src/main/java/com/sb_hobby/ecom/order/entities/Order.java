package com.sb_hobby.ecom.order.entities;

import com.sb_hobby.ecom.address.entities.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item")
    private Long orderId;

    @Email
    @Column(nullable = false)
    private String email;

    @NotNull(message = "Order date is required")
    @PastOrPresent(message = "Order date cannot be in the future")
    private LocalDate orderDate;

    @NotNull(message = "Total amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total amount must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Total amount must have at most 10 integer digits and 2 decimal places")
    private Double totalAmount;

    @NotBlank(message = "Order status is required")
    private String orderStatus;

    //uni-directional relationship
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

}
