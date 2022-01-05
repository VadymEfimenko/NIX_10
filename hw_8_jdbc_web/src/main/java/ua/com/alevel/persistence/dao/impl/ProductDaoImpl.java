package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.ProductDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductDaoImpl implements ProductDao {

    private static final String CREATE_PRODUCT_QUERY = "insert into products values (default,?,?,?,?,?)";
    private static final String UPDATE_PRODUCT_QUERY = "update products set price = ?, updated = ? where id = ";
    private static final String DELETE_PRODUCT_QUERY = "delete from products where id = ";
    private final static String EXIST_PRODUCT_BY_ID_QUERY = "select  count(*) from products where id = ";
    private final static String FIND_PRODUCT_BY_ID_QUERY = "select *,count(customer_id) as customerCount from products left join customer_product cp on products.id = cp.product_id where id = ";
    private final static String FIND_ALL_PRODUCTS_BY_CUSTOMERS_QUERY = "select * from products as p left join customer_product as cp on p.id = cp.product_id where cp.customer_id = ";
    private final static String PRODUCT_COUNT_QUERY = "select count(*) from products";
    private final static String FIND_ALL_PRODUCTS_QUERY = "select *,count(customer_id) as customerCount from products left join customer_product cp on products.id = cp.product_id group by products.id order by ";

    private final JpaConfig jpaConfig;

    public ProductDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Product entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_PRODUCT_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setBoolean(3, entity.getVisible());
            preparedStatement.setString(4, entity.getProductName());
            preparedStatement.setInt(5, entity.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Product entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_PRODUCT_QUERY + entity.getId())) {
            preparedStatement.setInt(1, entity.getPrice());
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_PRODUCT_QUERY + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean existById(Long id) {
        int count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_PRODUCT_BY_ID_QUERY + id)) {
            if (resultSet.next()) {
                count = resultSet.getInt("count(*)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count == 1;
    }

    @Override
    public Product findById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_PRODUCT_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                return initProductByResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public DataTableResponse<Product> findAll(DataTableRequest request) {
        List<Product> products = new ArrayList<>();
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        String sql = FIND_ALL_PRODUCTS_QUERY +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                products.add(initProductByResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DataTableResponse<Product> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(products);
        return dataTableResponse;
    }

    @Override
    public long count() {
        int count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(PRODUCT_COUNT_QUERY)) {
            if (resultSet.next()) {
                count = resultSet.getInt("count(*)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    @Override
    public Map<Long, String> findAllByCustomerId(Long customerId) {
        Map<Long, String> map = new HashMap<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_PRODUCTS_BY_CUSTOMERS_QUERY + customerId)) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String productName = resultSet.getString("product_name");
                map.put(id, productName);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return map;
    }

    private Product initProductByResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp update = resultSet.getTimestamp("updated");
        Boolean visible = resultSet.getBoolean("visible");
        String productName = resultSet.getString("product_name");
        Integer price = resultSet.getInt("price");
        Integer count = resultSet.getInt("customerCount");

        Product product = new Product();
        product.setId(id);
        product.setCreated(created);
        product.setUpdated(update);
        product.setVisible(visible);
        product.setProductName(productName);
        product.setPrice(price);
        product.setCustomerCount(count);

        return product;
    }
}
