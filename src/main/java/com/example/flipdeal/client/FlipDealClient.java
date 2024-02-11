package com.example.flipdeal.client;

import com.example.flipdeal.config.FlipDealConfig;
import com.example.flipdeal.dataTransferObject.Product;
import com.example.flipdeal.utils.HttpColler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Component
public class FlipDealClient {

    @Autowired
    private FlipDealConfig flipDealConfig;

    public List<Product> fatchProductsDetails() throws IOException {
        String response = HttpColler.get(flipDealConfig.getCoverselfHost().concat(flipDealConfig.getCoverSelfProductDetailsUri()) , null);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response, objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class));

    }

    public HashMap<String, Object> fatchINRDetails() throws IOException{

        String response = HttpColler.get(flipDealConfig.getCoverselfHost().concat(flipDealConfig.getCoverselfExchangeRatesUri()) , null);

        return (HashMap<String, Object>) new ObjectMapper().readValue(response, HashMap.class).get("rates");

    }

}
