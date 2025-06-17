package com.tskwn.assignment.controller;

import com.tskwn.assignment.dto.BrandCreateRequest;
import com.tskwn.assignment.dto.BrandCreateResponse;
import com.tskwn.assignment.dto.BrandDeleteResponse;
import com.tskwn.assignment.dto.BrandDto;
import com.tskwn.assignment.dto.BrandUpdateRequest;
import com.tskwn.assignment.dto.BrandUpdateResponse;
import com.tskwn.assignment.dto.ResponseDto;
import com.tskwn.assignment.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 브랜드 관련 컨트롤러.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/brand")
public class BrandController {
    private final BrandService brandService;

    /**
     * 브랜드 정보 생성.
     *
     * @param request 브랜드 생성 요청 정보
     * @return 생성된 브랜드 정보를 포함하는 ResponseDto Response
     */
    @PostMapping(value = "/create")
    public ResponseEntity<BrandCreateResponse> create(@RequestBody final BrandCreateRequest request) {
        BrandDto createdBrand = brandService.create(request.getBrandDto());
        return ResponseEntity.ok().body(BrandCreateResponse.builder().createdBrand(createdBrand).build());
    }

    /**
     * 브랜드 정보 생성.
     *
     * @param brandId  브랜드 아이디
     * @param request 브랜드 업데이트 정보
     * @return 업데이트된 브랜드 정보를 포함하는 ResponseDto Response
     */
    @PutMapping(value = "/{brandId}/update")
    public ResponseEntity<BrandUpdateResponse> update(@PathVariable("brandId") final Long brandId, @RequestBody final BrandUpdateRequest request) {
        BrandDto updatedBrand = brandService.update(brandId, request.getBrandDto());
        return ResponseEntity.ok().body(BrandUpdateResponse.builder().updatedBrand(updatedBrand).build());
    }

    /**
     * 브랜드 정보 삭제.
     *
     * @param brandId 브랜드 아이디
     * @return ResponseDto Response
     */
    @DeleteMapping(value = "/{brandId}/delete")
    public ResponseEntity<BrandDeleteResponse> delete(@PathVariable("brandId") final Long brandId) {
        brandService.delete(brandId);
        return ResponseEntity.ok().build();
    }
}
