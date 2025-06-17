package com.tskwn.assignment.dto;

import lombok.Builder;

@Builder
public class ProductCreateResponse extends ResponseDto {
    private ProductDto createdProduct;
}
