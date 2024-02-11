package com.example.flipdeal;

import com.example.flipdeal.client.FlipDealClient;
import com.example.flipdeal.constant.PromotionSet;
import com.example.flipdeal.dataTransferObject.DiscountDetails;
import com.example.flipdeal.dataTransferObject.Product;
import com.example.flipdeal.service.Context;
import com.example.flipdeal.service.PromotionStrategy;
import com.example.flipdeal.service.impl.PromotionA;
import com.example.flipdeal.service.impl.PromotionB;
import com.example.flipdeal.utils.Common;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class FlipDealApplication {

    public static void main(String[] args) throws IOException {

        //(i)subi bean bn k tiyar hojayge
        ConfigurableApplicationContext context = SpringApplication.run(FlipDealApplication.class, args);

        //(ii)subi bean get kerlege
        FlipDealClient flipDealClient = context.getBean(FlipDealClient.class);

        try {

            if (args.length == 0){
                throw new Exception("No argument");
            }

            List<Product> products = flipDealClient.fatchProductsDetails();
            Map<String, Object> currencyExchange = flipDealClient.fatchINRDetails();
            List<Product> updatedProducts = Common.changeCurrenyToINR(products, currencyExchange);

            PromotionStrategy promotionStrategy;

            PromotionSet promotionSet = PromotionSet.valueOf(args[0]);

            switch (promotionSet ) {

                case promotionSetA:
                    promotionStrategy = context.getBean(PromotionA.class);
                    break;
                case promotionSetB:
                    promotionStrategy = context.getBean(PromotionB.class);
                    break;
                default:
                    throw new IllegalArgumentException("No promotionSet found");

            }

            for (Product product : updatedProducts) {

                Context promotionContext = new Context(promotionStrategy);
                DiscountDetails discount = promotionContext.applyPermotion(product);
                product.setDiscount(discount);
                System.out.println("Product Category "+ product.getCategory()+" Product price "+product.getPrice()+" Discoumnt "+discount.getDiscountAmount()+" Discount Tag "+ discount.getDiscountTag());

            }

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("output.json"), products);

        } catch(Exception e){
            e.printStackTrace();
        }

    }

}

/*
Note: Terminal Command to Run this project.

  java -jar "C:\Users\asus\IdeaProjects\flipDeal\target\flipDeal-0.0.1-SNAPSHOT.jar" promotionSetA

----------------------------------------------------------------
  step(i) -> mvn clean install

  step(ii) -> editConfigration m jaker argument pass kerdo jo kerna ,
           promotionSetA,promotionSetB , play kerdo


*/
