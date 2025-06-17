package com.tskwn.assignment.controller;

import com.tskwn.assignment.dto.*;
import com.tskwn.assignment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 상품 관련 컨트롤러.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "/init")
    public ResponseEntity<Void> init() throws IOException {
        productService.initData();
        return ResponseEntity.ok().build();
    }

    /**
     * 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
     *
     * @return ProductDto List Response
     */
    @GetMapping(value = "/category-lowest-price")
    public ResponseEntity<CategoryLowestPriceDto> getCategoryLowestPrice() {
        List<ProductDto> productDtos = productService.getCategoryLowestPrice();
        Long total = productService.getTotal(productDtos);
        return ResponseEntity.ok(CategoryLowestPriceDto.builder().productDtoList(productDtos).total(total).build());
    }

    /**
     * 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
     *
     * @return BrandProductDto Response
     */
    @GetMapping(value = "/brand-category-lowest-price")
    public ResponseEntity<BrandProductDto> getBrandCategoryLowestPrice() {
        return ResponseEntity.ok(productService.getBrandCategoryLowestPrice());
    }

    /**
     * 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
     *
     * @param categoryName 카테고리명
     * @return CategoryLowestAndHighestPriceDto Response
     */
    @GetMapping(value = "/category-lowest-highest-price")
    public ResponseEntity<CategoryLowestAndHighestPriceDto> getCategoryLowestAndHighestPriceDto(@RequestParam(value = "category") final String categoryName) {
        return ResponseEntity.ok(productService.getCategoryLowestAndHighestPrice(categoryName));
    }


    /**
     * 상품정보 생성.
     *
     * @param request 상품 생성 요청 정보
     * @return ResponseDto Response
     */
    @PostMapping(value = "/create")
    public ResponseEntity<ProductCreateResponse> create(@RequestBody final ProductCreateRequest request) {
        ProductDto createdProduct = productService.create(request.getProductDto());
        return ResponseEntity.ok().body(ProductCreateResponse.builder().createdProduct(createdProduct).build());
    }

    /**
     * 상품정보 업데이트.
     *
     * @param productId  상품 아이디
     * @param request 상품 업데이트 요청 정보
     * @return ResponseDto Response
     */
    @PutMapping(value = "/{productId}/update")
    public ResponseEntity<ProductUpdateResponse> update(@PathVariable("productId") final Long productId, @RequestBody final ProductCreateRequest request) {
        ProductDto updatedProduct = productService.update(productId, request.getProductDto());
        return ResponseEntity.ok().body(ProductUpdateResponse.builder().updatedProduct(updatedProduct).build());
    }

    /**
     * 상품정보 삭제.
     *
     * @param productId 상품 아이디
     * @return ResponseDto Response
     */
    @DeleteMapping(value = "/{productId}/delete")
    public ResponseEntity<ProductDeleteResponse> delete(@PathVariable("productId") final Long productId) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }
}
