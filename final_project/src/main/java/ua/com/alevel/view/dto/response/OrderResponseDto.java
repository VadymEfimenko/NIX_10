package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.user.Order;
import ua.com.alevel.persistence.type.OrderStatus;

public class OrderResponseDto extends ResponseDto {

    private OrderStatus status;
    private String name;
    private String labelName;
    private String userEmail;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public OrderResponseDto(Order order) {
        setVisible(true);
        setCreated(order.getCreated());
        setUpdated(order.getUpdated());
        setId(order.getId());
        this.status = order.getOrderStatus();
        this.userEmail = order.getUser().getEmail();
        this.labelName = order.getLabel().getLabelName();
    }
}
