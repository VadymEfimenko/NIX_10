package ua.com.alevel.view.dto.request;

import ua.com.alevel.persistence.entity.release.Release;
import ua.com.alevel.persistence.type.OrderStatus;

import java.util.List;

public class OrderRequestDto extends RequestDto{
    private Long labelId;
    private OrderStatus status;
    private List<Release> list;

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Release> getList() {
        return list;
    }

    public void setList(List<Release> list) {
        this.list = list;
    }
}
