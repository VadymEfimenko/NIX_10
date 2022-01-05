package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.CustomerFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Customer;
import ua.com.alevel.service.CustomerService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.CustomerRequestDto;
import ua.com.alevel.view.dto.response.CustomerResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerFacadeImpl implements CustomerFacade {

    private final CustomerService customerService;

    public CustomerFacadeImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void create(CustomerRequestDto customerRequestDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerRequestDto.getFirstName());
        customer.setLastName(customerRequestDto.getLastName());
        List<Integer> productsId = customerRequestDto.getProductsId();
        customerService.create(customer);
        customerService.createBinding(customer, productsId);
    }

    @Override
    public void update(CustomerRequestDto customerRequestDto, Long id) {
        Customer customer = customerService.findById(id);
        customer.setFirstName(customerRequestDto.getFirstName());
        customer.setLastName(customerRequestDto.getLastName());
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
        DataTableResponse<Customer> tableResponse = customerService.findAll(dataTableRequest);

        List<CustomerResponseDto> customers = tableResponse.getItems().stream().
                map(CustomerResponseDto::new).
                collect(Collectors.toList());

        PageData<CustomerResponseDto> pageData = (PageData<CustomerResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(customers);
        return pageData;
    }

    @Override
    public Map<Long, String> findAllByProductId(Long productId) {
        return customerService.findAllByProductId(productId);
    }
}
