package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.ProductDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.service.ProductService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }


    @Override
    public void create(Product entity) {
        productDao.create(entity);
    }

    @Override
    public void update(Product entity) {
        productDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        productDao.delete(id);
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(id);
    }

    @Override
    public DataTableResponse<Product> findAll(DataTableRequest dataTableRequest) {
        DataTableResponse<Product> dataTableResponse = productDao.findAll(dataTableRequest);
        long count = productDao.count();
        WebResponseUtil.initDataTableResponse(dataTableRequest, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Map<Long, String> findAllCustomersByProductId(Long productId) {
        return productDao.findAllCustomersByProductId(productId);
    }
}
