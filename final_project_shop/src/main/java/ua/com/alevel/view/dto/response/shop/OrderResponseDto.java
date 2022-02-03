package ua.com.alevel.view.dto.response.shop;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.user.Order;
import ua.com.alevel.persistence.enums.OrderStatus;
import ua.com.alevel.view.dto.response.util.ResponseDto;

@Setter
@Getter
@ToString
public class OrderResponseDto extends ResponseDto {

    private OrderStatus status;
    private String name;
    private String distributorName;
    private String userEmail;

    public OrderResponseDto(Order order) {
        setVisible(true);
        setCreated(order.getCreated());
        setUpdated(order.getUpdated());
        setId(order.getId());
        this.status = order.getOrderStatus();
        this.userEmail = order.getUser().getEmail();
        this.distributorName = order.getDistributor().getName();
    }

}
