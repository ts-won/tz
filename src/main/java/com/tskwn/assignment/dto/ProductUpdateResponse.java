package com.tskwn.assignment.dto;

import lombok.Builder;

@Builder
public class ProductUpdateResponse extends ResponseDto {
    private ProductDto updatedProduct;
}
