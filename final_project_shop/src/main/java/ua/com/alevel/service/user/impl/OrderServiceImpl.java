package ua.com.alevel.service.user.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.entity.user.Order;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.repository.AdminRepository;
import ua.com.alevel.persistence.repository.OrderRepository;
import ua.com.alevel.persistence.repository.PersonalRepository;
import ua.com.alevel.service.user.OrderService;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final CrudRepositoryHelper<Order, OrderRepository> crudRepositoryHelper;
    private final OrderRepository orderRepository;
    private final PersonalRepository personalRepository;
    private final AdminRepository adminRepository;

    public OrderServiceImpl(CrudRepositoryHelper<Order, OrderRepository> crudRepositoryHelper,
                            OrderRepository orderRepository,
                            PersonalRepository personalRepository,
                            AdminRepository adminRepository) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.orderRepository = orderRepository;
        this.personalRepository = personalRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public void create(Order entity) {
        crudRepositoryHelper.create(orderRepository, entity);
    }

    @Override
    public void update(Order entity) {
        crudRepositoryHelper.update(orderRepository, entity);
    }

    @Override
    public void delete(Long id) {
        crudRepositoryHelper.delete(orderRepository, id);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return crudRepositoryHelper.findById(orderRepository, id);
    }

    @Override
    public DataTableResponse<Order> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(orderRepository, request);
    }

    @Override
    public Personal findUserByEmail(String email) {
        return personalRepository.findByEmail(email);
    }

    @Override
    public Admin findAdminByEmail(String email) {
        return adminRepository.findAdminByEmail(email);
    }
}
