package com.example.flipdeal.utils;

import com.example.flipdeal.constant.Currency;
import com.example.flipdeal.dataTransferObject.Product;

import java.util.List;
import java.util.Map;

public class Common {

    public static double calculateDiscount(int basePrice , double persentage){
        return basePrice * persentage % 100;
    }

    public static List<Product> changeCurrenyToINR(List<Product> products , Map<String, Object> currencyExchange){

        for(Product product : products){
             if( !Currency.INR.name().equalsIgnoreCase(product.getCurrency())  ){
                product.setPrice( (int)calculatePriceInINR(product , product.getPrice() , currencyExchange)  );
             }
        }

        return products;

    }

    public static double calculatePriceInINR(Product product , int productPrice , Map<String, Object> currencyExchange){
        return ( (double)currencyExchange.get(product.getCurrency()) * productPrice );
    }

}
