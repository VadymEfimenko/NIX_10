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
    public DataTableResponse<Product> findAll(DataTableRequest request) {
        DataTableResponse<Product> dataTableResponse = productDao.findAll(request);
        long count = productDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Map<Long, String> findAllByCustomerId(Long customerId) {
        return productDao.findAllByCustomerId(customerId);
    }
}
