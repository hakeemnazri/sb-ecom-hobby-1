package com.sb_hobby.ecom.payment.entities;

import com.sb_hobby.ecom.order.entities.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @NotBlank
    @Size(min = 4, message = "Payment method must contain at least 4 characters")
    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "pg_payment_id")
    private String pgPaymentId;

    @Column(name = "pg_status")
    private String pgStatus;

    @Column(name = "pg_response_message")
    private String pgResponseMessage;

    @Column(name = "pg_name")
    private String pgName;

    @OneToOne(mappedBy = "payment", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Order order;
}
