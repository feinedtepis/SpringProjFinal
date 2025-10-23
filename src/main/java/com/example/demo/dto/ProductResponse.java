package com.example.demo.dto;

import com.example.demo.model.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.Date;

@Data
public class ProductResponse {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private Date date;
    private String imageBase64;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();

        this.imageBase64 = (product.getImage() != null)
                ? Base64.getEncoder().encodeToString(product.getImage())
                : null;
    }
}
