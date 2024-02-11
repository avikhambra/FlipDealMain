package com.example.flipdeal.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class FlipDealConfig {

    //A
    @Value("${coverself.host}")
    private String coverselfHost;

    //b
    @Value("${coverself.product.details.uri}")
    private String coverSelfProductDetailsUri;

    //c
    @Value("${coverself.exchange.rates.uri}")
    private String coverselfExchangeRatesUri;


    private int two = 2;

    private int four = 5;
    private int seven = 7;

    private int eight = 8;

    private int twelve = 12;

    private int inventoryCount = 20;

    private int fiveHundred = 500;

    private int hundred = 100;

}