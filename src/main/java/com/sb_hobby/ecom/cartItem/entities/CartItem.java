package com.sb_hobby.ecom.cartItem.entities;

import com.sb_hobby.ecom.cart.entities.Cart;
import com.sb_hobby.ecom.product.entities.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 999, message = "Quantity cannot exceed 999")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull(message = "Discount cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Discount cannot be negative")
    @DecimalMax(value = "100.0", inclusive = true, message = "Discount cannot exceed 100%")
    @Digits(integer = 3, fraction = 2, message = "Discount must have at most 3 integer digits and 2 decimal places")
    @Column(name = "discount", precision = 5, scale = 2, nullable = false)
    private double discount;

    @NotNull(message = "Product price cannot be null")
    @DecimalMin(value = "0.01", message = "Product price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Product price must have at most 10 integer digits and 2 decimal places")
    @Column(name = "product_price", precision = 12, scale = 2, nullable = false)
    private double productPrice;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
