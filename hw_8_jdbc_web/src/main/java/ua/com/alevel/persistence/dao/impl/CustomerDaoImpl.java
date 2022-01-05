package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.CustomerDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerDaoImpl implements CustomerDao {

    private static final String CREATE_CUSTOMER_QUERY = "insert into customers values(default,?,?,?,?,?)";
    private static final String UPDATE_CUSTOMER_QUERY = "update customers set first_name = ?, last_name = ?, updated = ? WHERE id = ";
    private static final String DELETE_CUSTOMER_QUERY = "delete from customers where id = ";
    private static final String EXIST_CUSTOMER_BY_ID_QUERY = "select count(*) from customers where id = ";
    private static final String CUSTOMER_COUNT_QUERY = "select count(*) from customers";
    private static final String FIND_CUSTOMER_BY_ID_QUERY = "select *,count(product_id) as productCount from customers left join customer_product cp on customers.id = cp.customer_id where id = ";
    private static final String FIND_ALL_CUSTOMERS_QUERY = "select *,count(product_id) as productCount from customers left join customer_product cp on customers.id = cp.customer_id group by customers.id order by ";
    private static final String FIND_ALL_CUSTOMERS_BY_PRODUCT_QUERY = "select * from customers as c left join customer_product as cp on c.id = cp.customer_id where cp.product_id = ";


    private final JpaConfig jpaConfig;

    public CustomerDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Customer entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_CUSTOMER_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setBoolean(3, entity.getVisible());
            preparedStatement.setString(4, entity.getFirstName());
            preparedStatement.setString(5, entity.getLastName());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void update(Customer entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_CUSTOMER_QUERY + entity.getId())) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setTimestamp(3, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_CUSTOMER_QUERY + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean existById(Long id) {
        int count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_CUSTOMER_BY_ID_QUERY + id)) {
            if (resultSet.next()) {
                count = resultSet.getInt("count(*)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count == 1;
    }

    @Override
    public Customer findById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_CUSTOMER_BY_ID_QUERY + id)) {
            if (resultSet.next()) {
                return initCustomerByResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public DataTableResponse<Customer> findAll(DataTableRequest request) {
        List<Customer> customers = new ArrayList<>();
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        String sql = FIND_ALL_CUSTOMERS_QUERY +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                customers.add(initCustomerByResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DataTableResponse<Customer> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(customers);
        return tableResponse;
    }

    @Override
    public long count() {
        int count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(CUSTOMER_COUNT_QUERY)) {
            if (resultSet.next()) {
                count = resultSet.getInt("count(*)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    @Override
    public Map<Long, String> findAllByProductId(Long productId) {
        Map<Long, String> map = new HashMap<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_CUSTOMERS_BY_PRODUCT_QUERY + productId)) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String fullName = firstName + " " + lastName;
                map.put(id, fullName);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return map;
    }

    @Override
    public void createBinding(Customer customer, List<Integer> productsId) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("insert into customer_product values (LAST_INSERT_ID(),?)")) {
            for (Integer i : productsId) {
                preparedStatement.setLong(1, i);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Customer initCustomerByResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean visible = resultSet.getBoolean("visible");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        Integer productCount = resultSet.getInt("productCount");

        Customer customer = new Customer();
        customer.setId(id);
        customer.setCreated(created);
        customer.setUpdated(updated);
        customer.setVisible(visible);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setProductCount(productCount);
        return customer;
    }
}
