package com.example.flipdeal.controller;

import com.example.flipdeal.FlipDealApplication;
import com.example.flipdeal.client.FlipDealClient;
import com.example.flipdeal.constant.PromotionSet;
import com.example.flipdeal.dataTransferObject.DiscountDetails;
import com.example.flipdeal.dataTransferObject.Product;
import com.example.flipdeal.service.PromotionStrategy;
import com.example.flipdeal.utils.Common;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/v1/apply-promotion", produces = MediaType.APPLICATION_JSON_VALUE)
public class FlipDealController {

    @Autowired
    @Qualifier("promotionImplA")
     private PromotionStrategy promotionStrategyA;

    @Autowired
    @Qualifier("promotionImplB")
     private PromotionStrategy promotionStrategyB;

    PromotionStrategy promotionStrategy;

    @Autowired
    private FlipDealClient flipDealClient;


    @RequestMapping( value = {"/{promotionType}"}, method = {RequestMethod.GET} )
    public List<Product> applyPromostionController(@PathVariable String promotionType) {

        List<Product> products = null;
        try {
            products = flipDealClient.fatchProductsDetails();

            Map<String, Object> currencyExchange = flipDealClient.fatchINRDetails();
            List<Product> updatedProducts = Common.changeCurrenyToINR(products, currencyExchange);

             switch (PromotionSet.valueOf(promotionType)) {

                 case promotionSetA:

                     for (Product product : updatedProducts) {

                         DiscountDetails discount = promotionStrategyA.claculateDiscountFromPermotion(product);
                         product.setDiscount(discount);
                         System.out.println("Product Category "+ product.getCategory()+" Product price "+product.getPrice()+" Discoumnt "+discount.getDiscountAmount()+" Discount Tag "+ discount.getDiscountTag());

                     }
                     return products;

                 case promotionSetB:

                     for (Product product : updatedProducts) {

                         DiscountDetails discount = promotionStrategyB.claculateDiscountFromPermotion(product);
                         product.setDiscount(discount);
                         System.out.println("Product Category "+ product.getCategory()+" Product price "+product.getPrice()+" Discoumnt "+discount.getDiscountAmount()+" Discount Tag "+ discount.getDiscountTag());


                     }
                     return products;

                 default:

                     throw new IllegalArgumentException("No promotionSet found");
                     

             }


        } catch (IOException e) {
                throw new RuntimeException(e);
        }

    }

}

/*
Note : hum direct call nhi kerge, application ek bar he run hoga , chalta rhega
        hum chalte application m he value pass kerdege bar bar run hi kerge.

 */

