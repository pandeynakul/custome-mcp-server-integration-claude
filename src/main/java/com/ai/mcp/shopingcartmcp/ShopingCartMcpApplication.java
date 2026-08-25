package com.ai.mcp.shopingcartmcp;

import com.ai.mcp.shopingcartmcp.tools.ShoppingCartMcpService;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ai.mcp.shopingcartmcp.repository")
@EntityScan(basePackages = "com.ai.mcp.shopingcartmcp.entity")
public class ShopingCartMcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopingCartMcpApplication.class, args);
    }

    @Bean
    public List<ToolCallback> shoppingCartToolCallback(ShoppingCartMcpService shoppingCartMcpService) {
        return List.of(ToolCallbacks.from(shoppingCartMcpService));
    }
}
