package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.ProductRequestDto;
import ua.com.alevel.view.dto.response.ProductResponseDto;

import java.util.Map;

public interface ProductFacade extends BaseFacade<ProductRequestDto, ProductResponseDto> {
    Map<Long, String> findAllCustomersByProductId(Long productId);
}
