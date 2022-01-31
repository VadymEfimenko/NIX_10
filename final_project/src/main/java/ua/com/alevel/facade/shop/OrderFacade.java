package ua.com.alevel.facade.shop;

import ua.com.alevel.facade.BaseFacade;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.view.dto.request.OrderRequestDto;
import ua.com.alevel.view.dto.response.OrderResponseDto;

public interface OrderFacade extends BaseFacade<OrderRequestDto, OrderResponseDto> {

    Personal findUserByEmail(String email);

    Admin findAdminByEmail(String email);
}
