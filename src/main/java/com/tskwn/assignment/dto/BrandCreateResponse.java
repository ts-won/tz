package com.tskwn.assignment.dto;

import lombok.Builder;

@Builder
public class BrandCreateResponse extends ResponseDto {
    private BrandDto createdBrand;
}
