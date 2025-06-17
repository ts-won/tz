package com.tskwn.assignment.dto;

import lombok.Builder;

@Builder
public class BrandUpdateResponse extends ResponseDto {
    private BrandDto updatedBrand;
}
