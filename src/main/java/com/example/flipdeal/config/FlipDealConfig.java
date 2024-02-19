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




    @Value("${africa.origin.percentage}")
    private String africaOrigin;

    @Value("${discount.four}")
    private String discountFour;

    @Value("${discount.eight}")
    private String discountEight;

    @Value("${discount.hundred}")
    private String discountHundred;

    @Value("${discount.twelve}")
    private String discountTwelve;

    @Value("${inventory.count}")
    private String inventoryCount;

    @Value("${discount.seven}")
    private String discountSeven;

    @Value("${price.fivehundred}")
    private String priceFivehundred;


}