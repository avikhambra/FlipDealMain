package com.example.flipdeal.service;

import com.example.flipdeal.dataTransferObject.DiscountDetails;
import com.example.flipdeal.dataTransferObject.Product;

public interface PromotionStrategy {
    public DiscountDetails claculateDiscountFromPermotion(Product product);
}

/*
Note: Run time per change hoo rhaa isliye stratagy  desgin pattren ka use kiya.
 */
