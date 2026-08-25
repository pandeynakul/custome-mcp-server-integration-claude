package com.ai.mcp.shopingcartmcp.repository;

/* author :  Ankul Deshpande */

import com.ai.mcp.shopingcartmcp.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {

    CartItem findByProductId(String productId);

    void deleteByProductId(String productId);
}
