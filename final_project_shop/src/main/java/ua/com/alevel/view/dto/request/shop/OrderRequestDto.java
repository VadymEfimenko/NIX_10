package ua.com.alevel.view.dto.request.shop;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.shop.Release;
import ua.com.alevel.persistence.enums.OrderStatus;
import ua.com.alevel.view.dto.request.util.RequestDto;

import java.util.List;

@Setter
@Getter
public class OrderRequestDto extends RequestDto {

    private Long distributorId;
    private OrderStatus status;
    private List<Release> releases;
}
