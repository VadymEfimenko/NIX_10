package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.CustomerDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Customer;
import ua.com.alevel.service.CustomerService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void create(Customer entity) {
        customerDao.create(entity);
    }

    @Override
    public void update(Customer entity) {
        customerDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        customerDao.delete(id);
    }

    @Override
    public Customer findById(Long id) {
        return customerDao.findById(id);
    }

    @Override
    public DataTableResponse<Customer> findAll(DataTableRequest request) {
        DataTableResponse<Customer> dataTableResponse = customerDao.findAll(request);
        long count = customerDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }


    @Override
    public Map<Long, String> findAllByProductId(Long productId) {
        return customerDao.findAllByProductId(productId);
    }

    @Override
    public void createBinding(Customer customer, List<Integer> productsId) {
        customerDao.createBinding(customer, productsId);
    }


}
