package com.sb_hobby.ecom.cart.entities;

import com.sb_hobby.ecom.cartItem.entities.CartItem;
import com.sb_hobby.ecom.user.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    @NotNull(message = "Total price cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Total price cannot be negative")
    @Digits(integer = 10, fraction = 2, message = "Total price must have at most 10 integer digits and 2 decimal places")
    @Column(name = "total_price", precision = 12, scale = 2)
    private Double totalPrice = 0.0;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            mappedBy = "cart",
            orphanRemoval = true,
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER
    )
    private Set<CartItem> cartItems = new HashSet<>();
}
