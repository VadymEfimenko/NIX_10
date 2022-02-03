package ua.com.alevel.facade.shop;

import ua.com.alevel.facade.util.BaseFacade;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.view.dto.request.shop.OrderRequestDto;
import ua.com.alevel.view.dto.response.shop.OrderResponseDto;

public interface OrderFacade extends BaseFacade<OrderRequestDto, OrderResponseDto> {

    Personal findUserByEmail(String email);

    Admin findAdminByEmail(String email);
}
