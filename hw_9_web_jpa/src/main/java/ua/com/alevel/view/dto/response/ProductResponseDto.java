package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Product;

public class ProductResponseDto extends ResponseDto {

    private String name;
    private Integer price;
    private Integer countCustomers;

    public ProductResponseDto() {
    }

    public ProductResponseDto(Product product) {
        setId(product.getId());
        setCreated(product.getCreated());
        setUpdated(product.getUpdated());
        this.name = product.getName();
        this.price = product.getPrice();
        this.countCustomers = product.getCustomers().size();
    }

    public String getProductName() {
        return name;
    }

    public void setProductName(String name) {
        this.name = name;
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
