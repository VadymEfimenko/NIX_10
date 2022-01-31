package ua.com.alevel.facade.shop.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.shop.OrderFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.release.Release;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.entity.user.Order;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.type.OrderStatus;
import ua.com.alevel.service.shop.LabelService;
import ua.com.alevel.service.shop.OrderService;
import ua.com.alevel.service.shop.ReleaseService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.OrderRequestDto;
import ua.com.alevel.view.dto.response.OrderResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderFacadeImpl implements OrderFacade {

    private final OrderService orderService;
    private final LabelService labelService;
    private final ReleaseService releaseService;

    public OrderFacadeImpl(OrderService orderService, LabelService labelService, ReleaseService releaseService) {
        this.orderService = orderService;
        this.labelService = labelService;
        this.releaseService = releaseService;
    }

    @Override
    public void create(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setCreated(new Date(System.currentTimeMillis()));
        order.setUpdated(new Date(System.currentTimeMillis()));
        order.setLabel(labelService.findById(orderRequestDto.getLabelId()).get());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        order.setUser(orderService.findUserByEmail(auth.getName()));
        order.setOrderStatus(OrderStatus.MADE);
        Set<Release> releases = new HashSet<>(orderRequestDto.getList());
        orderService.create(order);
        for (Release release: releases) {
            release.getOrders().add(order);
            releaseService.update(release);
        }
    }

    @Override
    public void update(OrderRequestDto orderRequestDto, Long id) {
        Order order = orderService.findById(id).get();
        order.setUpdated(new Date(System.currentTimeMillis()));
        order.setOrderStatus(orderRequestDto.getStatus());
        orderService.update(order);
    }

    @Override
    public void delete(Long id) {
        orderService.delete(id);
    }

    @Override
    public OrderResponseDto findById(Long id) {
        return new OrderResponseDto(orderService.findById(id).get());
    }

    @Override
    public PageData<OrderResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Order> tableResponse = orderService.findAll(dataTableRequest);

        List<OrderResponseDto> orders = tableResponse.getItems().stream().
                map(OrderResponseDto::new).
                collect(Collectors.toList());

        PageData<OrderResponseDto> pageData = (PageData<OrderResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(orders);
        return pageData;
    }

    @Override
    public Personal findUserByEmail(String email) {
        return orderService.findUserByEmail(email);
    }

    @Override
    public Admin findAdminByEmail(String email) {
        return orderService.findAdminByEmail(email);
    }
}
