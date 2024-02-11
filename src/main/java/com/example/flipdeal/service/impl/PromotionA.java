package com.example.flipdeal.service.impl;

import com.example.flipdeal.config.FlipDealConfig;
import com.example.flipdeal.constant.Category;
import com.example.flipdeal.constant.Origin;
import com.example.flipdeal.dataTransferObject.DiscountDetails;
import com.example.flipdeal.dataTransferObject.Product;
import com.example.flipdeal.service.PromotionStrategy;
import com.example.flipdeal.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionA implements PromotionStrategy {

    @Autowired
    private FlipDealConfig flipDealConfig;

    public DiscountDetails claculateDiscountFromPermotion(Product product){

          double discount = 0;
          String permotionTag = "";

          if(  (Origin.AFRICA.name().equalsIgnoreCase(product.getOrigin())  )){
              discount = Math.max(discount , ( Common.calculateDiscount(product.getPrice() , flipDealConfig.getSeven()) ));
              permotionTag = "get 7% off.";
          }


           if(product.getRating() == flipDealConfig.getTwo() ){
              discount = Math.max(discount , (Common.calculateDiscount(product.getPrice() , flipDealConfig.getFour()) ));
              permotionTag = "get 4% off.";
           }else if (product.getRating() < flipDealConfig.getTwo() ){
              discount = Math.max(discount , ( Common.calculateDiscount(product.getPrice() , flipDealConfig.getEight()) ));
              permotionTag = "get 8% off.";
           }

           if( ( Category.ELECTRONICS.name().equalsIgnoreCase(product.getCategory() ) ||  ( Category.FURNISHING.name().equalsIgnoreCase(product.getCategory()) )  )){

              if(product.getPrice() > flipDealConfig.getFiveHundred()){
                  discount = Math.max(discount , Common.calculateDiscount(product.getPrice() , flipDealConfig.getHundred()) );
                  permotionTag = "get 100Rs off.";
              }

           }

          DiscountDetails discountDetails = new DiscountDetails();
          discountDetails.setDiscountAmount(discount);
          discountDetails.setDiscountTag(permotionTag);

          return discountDetails;

    }

}



