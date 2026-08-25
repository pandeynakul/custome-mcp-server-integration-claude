package com.ai.mcp.shopingcartmcp.tools;

/* author :  Ankul Deshpande */

import com.ai.mcp.shopingcartmcp.entity.CartItem;
import com.ai.mcp.shopingcartmcp.repository.CartItemRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartMcpService {

    private CartItemRepository cartItemRepository;

    public ShoppingCartMcpService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    //tools
    //inventory:
    //consider these valuse are getting form DB
    //hardcoded here
    private static final Map<String, Double> PRODUCT = Map.of(
            "iphnone", 7999.0,
            "MacBook Air", 17999.0,
            "Boat Airphones", 1999.0
    );

    @Tool(name = "addToCart", description = "Add the product to shopping cart." +
            "If product cart exists please update the quantity")
    public String addToCart(@ToolParam String productName, @ToolParam int quantity) {

        if (!PRODUCT.containsKey(productName)) {
            return "product -Not found";
        }
        Double price = PRODUCT.get(productName);
        CartItem cartItem = cartItemRepository.findByProductId(productName);
        if (cartItem == null) {
            //if null then create and add
            cartItem = new CartItem();
            cartItem.setProductId(productName);
            cartItem.setProductName(productName);
            cartItem.setQuantity(quantity);

        } else {
            //add to it db with increase quantity
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItem.setPrice(cartItem.getQuantity()*price);
        cartItemRepository.save(cartItem);
        return quantity + "" + productName + " added to cart total price: " + (cartItem.getPrice());
    }

    @Tool(name = "removeCart", description = "remove item(s)  from shopping cart")
    @Transactional
    public String removeCart(@ToolParam String productName, @ToolParam int quantityToRemove) {
        CartItem cartItem = cartItemRepository.findByProductId(productName);
        if (cartItem == null) {
            return "Product not found in cart";
        }
        // Calculate unit price (total price / quantity)
        double unitPrice = cartItem.getPrice() / cartItem.getQuantity();
        // Case 1: Remove specific quantity
        if (quantityToRemove > 0 && cartItem.getQuantity() > quantityToRemove) {
            // Decrement quantity
            cartItem.setQuantity(cartItem.getQuantity() - quantityToRemove);
            // Update price based on new quantity
            cartItem.setPrice(cartItem.getQuantity() * unitPrice);
            cartItemRepository.save(cartItem);
            return quantityToRemove + " " + productName + " removed. Remaining quantity: " + cartItem.getQuantity();
        }
        // Case 2: Remove all (either quantityToRemove >= current quantity OR explicit full removal)
        cartItemRepository.deleteByProductId(productName);
        return productName + " completely removed from cart";
    }

    @Tool(name = "getCarts", description = "Retrieve the current shopping cart items")
    public List<CartItem> getCarts() {
        return cartItemRepository.findAll();


    }

    @Tool(name = "getCartTotal", description = "calculate the total price of items in the shopping cart")
    public Double getCartTotal(@ToolParam String productName) {
        return cartItemRepository.findAll().stream().mapToDouble(CartItem::getPrice).sum();
    }
}
