package com.example.flipdeal.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private String category;
    private int inventory;
    private double rating;
    private String currency;
    private int price;
    private String origin;
    private String product;
    private String arrival;
    private DiscountDetails discount;

}
