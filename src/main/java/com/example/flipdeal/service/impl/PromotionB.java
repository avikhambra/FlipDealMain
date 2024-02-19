package com.example.flipdeal.service.impl;

import com.example.flipdeal.config.FlipDealConfig;
import com.example.flipdeal.constant.Arrival;
import com.example.flipdeal.dataTransferObject.DiscountDetails;
import com.example.flipdeal.dataTransferObject.Product;
import com.example.flipdeal.service.PromotionStrategy;
import com.example.flipdeal.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("promotionImplB")
public class PromotionB implements PromotionStrategy {

    @Autowired
    private FlipDealConfig flipDealConfig;

    public DiscountDetails claculateDiscountFromPermotion(Product product){

        double discount = 0;
        String permotionTag = "";

        if(product.getInventory() > Integer.parseInt( flipDealConfig.getInventoryCount() )){
            discount = Math.max(discount , Common.calculateDiscount(product.getPrice() , Double.parseDouble(flipDealConfig.getDiscountTwelve())));
            permotionTag = "get 12% off";
        }else if( Arrival.NEW.name().equalsIgnoreCase(product.getArrival()) ){
            discount = Math.max(discount , Common.calculateDiscount(product.getPrice() , Double.parseDouble(flipDealConfig.getDiscountSeven())));
            permotionTag = "get 7% off";
        }

        DiscountDetails discountDetails = new DiscountDetails();
        discountDetails.setDiscountAmount(discount);
        discountDetails.setDiscountTag(permotionTag);

        return discountDetails;

    }

}
