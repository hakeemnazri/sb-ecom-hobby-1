package com.sb_hobby.ecom.orderItem.entities;

import com.sb_hobby.ecom.order.entities.Order;
import com.sb_hobby.ecom.product.entities.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

@Entity
@Table(name = "order_items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 10000, message = "Quantity cannot exceed 10000")
    private Integer quantity;

    @NotNull(message = "Discount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Discount cannot be negative")
    @DecimalMax(value = "100.0", inclusive = true, message = "Discount cannot exceed 100%")
    @Digits(integer = 3, fraction = 2, message = "Discount must have at most 3 integer digits and 2 decimal places")
    private double discount;

    @NotNull(message = "Ordered product price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Ordered product price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Ordered product price must have at most 10 integer digits and 2 decimal places")
    private double orderedProductPrice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
