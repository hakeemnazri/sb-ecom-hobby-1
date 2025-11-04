package com.sb_hobby.ecom.order.entities;

import com.sb_hobby.ecom.address.entities.Address;
import com.sb_hobby.ecom.orderItem.entities.OrderItem;
import com.sb_hobby.ecom.payment.entities.Payment;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(
            mappedBy = "order",
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

}
