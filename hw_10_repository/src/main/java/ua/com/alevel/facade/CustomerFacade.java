package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.CustomerRequestDto;
import ua.com.alevel.view.dto.response.CustomerResponseDto;

import java.util.Map;

public interface CustomerFacade extends BaseFacade<CustomerRequestDto, CustomerResponseDto> {
    Map<Long, String> findAllProductsByCustomerId(Long id);
}
