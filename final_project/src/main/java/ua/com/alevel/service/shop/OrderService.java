package ua.com.alevel.service.shop;

import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.entity.user.Order;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.service.BaseService;

public interface OrderService extends BaseService<Order> {
    Personal findUserByEmail(String email);
    Admin findAdminByEmail(String email);
}
