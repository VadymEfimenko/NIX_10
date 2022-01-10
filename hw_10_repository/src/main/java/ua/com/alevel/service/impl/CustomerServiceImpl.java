package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Customer;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.persistence.repository.CustomerRepository;
import ua.com.alevel.service.CustomerService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CrudRepositoryHelper<Customer, CustomerRepository> crudRepositoryHelper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CrudRepositoryHelper<Customer, CustomerRepository> crudRepositoryHelper,
                               CustomerRepository customerRepository) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.customerRepository = customerRepository;
    }

    @Override
    public void create(Customer entity) {
    crudRepositoryHelper.create(customerRepository, entity);
    }

    @Override
    public void update(Customer entity) {
    crudRepositoryHelper.update(customerRepository, entity);
    }

    @Override
    public void delete(Long id) {
    crudRepositoryHelper.delete(customerRepository, id);
    }

    @Override
    public Customer findById(Long id) {
        return crudRepositoryHelper.findById(customerRepository, id).get();
    }

    @Override
    public DataTableResponse<Customer> findAll(DataTableRequest dataTableRequest) {
        return crudRepositoryHelper.findAll(customerRepository, dataTableRequest);
    }

    @Override
    public Map<Long, String> findAllProductsByCustomerId(Long customerId) {
        Map<Long, String> allProducts = new HashMap<>();
        Set<Product> products = findById(customerId).getProducts();
        for (Product product : products){
            allProducts.put(product.getId(), product.getName());
        }
        return allProducts;
    }
}
