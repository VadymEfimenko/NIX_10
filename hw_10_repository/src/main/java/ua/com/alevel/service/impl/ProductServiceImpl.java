package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Customer;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.persistence.repository.ProductRepository;
import ua.com.alevel.service.CustomerService;
import ua.com.alevel.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final CrudRepositoryHelper<Product, ProductRepository> crudRepositoryHelper;
    private final ProductRepository productRepository;
    private final CustomerService customerService;

    public ProductServiceImpl(CrudRepositoryHelper<Product, ProductRepository> crudRepositoryHelper,
                              ProductRepository productRepository,
                              CustomerService customerService) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.productRepository = productRepository;
        this.customerService = customerService;
    }

    @Override
    public void create(Product entity) {
        crudRepositoryHelper.create(productRepository, entity);
    }

    @Override
    public void update(Product entity) {
        crudRepositoryHelper.update(productRepository, entity);
    }

    @Override
    public void delete(Long id) {
        Product product = crudRepositoryHelper.findById(productRepository, id).get();
        List<Customer> customers = product.getCustomers().stream().
                filter(customer -> customerService.findAllProductsByCustomerId(customer.getId()).size() == 1).
                collect(Collectors.toList());
        product.getCustomers().retainAll(customers);
        crudRepositoryHelper.update(productRepository, product);
        crudRepositoryHelper.delete(productRepository, id);
    }

    @Override
    public Product findById(Long id) {
        return crudRepositoryHelper.findById(productRepository, id).get();
    }

    @Override
    public DataTableResponse<Product> findAll(DataTableRequest dataTableRequest) {
        return crudRepositoryHelper.findAll(productRepository, dataTableRequest);
    }

    @Override
    public Map<Long, String> findAllCustomersByProductId(Long productId) {
        Map<Long, String> allCustomers = new HashMap<>();
        Set<Customer> customers = findById(productId).getCustomers();
        for (Customer customer : customers){
            allCustomers.put(customer.getId(), customer.getFirstName() + " " + customer.getSecondName());
        }
        return allCustomers;
    }
}
