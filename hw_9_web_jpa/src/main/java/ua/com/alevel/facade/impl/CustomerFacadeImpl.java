package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.CustomerFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Customer;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.service.CustomerService;
import ua.com.alevel.service.ProductService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.CustomerRequestDto;
import ua.com.alevel.view.dto.response.CustomerResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerFacadeImpl implements CustomerFacade {

    private final ProductService productService;
    private final CustomerService customerService;

    public CustomerFacadeImpl(ProductService productService, CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
    }

    @Override
    public void create(CustomerRequestDto requestDto) {
        Customer customer = new Customer();
        customer.setFirstName(requestDto.getFirstName());
        customer.setSecondName(requestDto.getSecondName());
        try {
            Set<Long> productsId = requestDto.getProductsId();
            customerService.create(customer);
            for (Long id : productsId) {
                Product product = productService.findById(id);
                product.addCustomer(customer);
                productService.update(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CustomerRequestDto requestDto, Long id) {
        Customer customer = customerService.findById(id);
        customer.setFirstName(requestDto.getFirstName());
        customer.setSecondName(requestDto.getSecondName());
        customer.setUpdated(new Timestamp(System.currentTimeMillis()));
        customerService.update(customer);
    }

    @Override
    public void delete(Long id) {
        customerService.delete(id);
    }

    @Override
    public CustomerResponseDto findById(Long id) {
        return new CustomerResponseDto(customerService.findById(id));
    }

    @Override
    public PageData<CustomerResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Customer> dataTableResponse = customerService.findAll(dataTableRequest);
        List<CustomerResponseDto> customers = dataTableResponse.getItems().stream().
                map(CustomerResponseDto::new).
                collect(Collectors.toList());
        PageData<CustomerResponseDto> pageData = (PageData<CustomerResponseDto>) WebResponseUtil.initPageData(dataTableResponse);
        pageData.setItems(customers);
        return pageData;
    }

    @Override
    public Map<Long, String> findAllProductsByCustomerId(Long id) {
        return customerService.findAllProductsByCustomerId(id);
    }
}
