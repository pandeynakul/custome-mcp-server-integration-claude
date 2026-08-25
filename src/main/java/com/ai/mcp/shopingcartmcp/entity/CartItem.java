package com.ai.mcp.shopingcartmcp.entity;

/* author :  Ankul Deshpande */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;


@Entity
@Table(name = "cart_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @UuidGenerator
    private String id;
    private String productId;
    private String productName;
    private double price;
    private int quantity;


}
