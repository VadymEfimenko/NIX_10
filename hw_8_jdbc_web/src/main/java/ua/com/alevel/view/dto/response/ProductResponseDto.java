package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Product;

public class ProductResponseDto extends ResponseDto {

    private String productName;
    private Integer price;
    private Integer countCustomers;

    public ProductResponseDto() {
    }

    public ProductResponseDto(Product product) {
        setId(product.getId());
        setCreated(product.getCreated());
        setUpdated(product.getUpdated());
        setVisible(product.getVisible());
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.countCustomers = product.getCustomerCount();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCountCustomers() {
        return countCustomers;
    }

    public void setCountCustomers(Integer countCustomers) {
        this.countCustomers = countCustomers;
    }
}
