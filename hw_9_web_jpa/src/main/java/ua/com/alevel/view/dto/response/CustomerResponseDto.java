package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Customer;

public class CustomerResponseDto extends ResponseDto {

    private String firstName;
    private String lastName;
    private Integer productCount;

    public CustomerResponseDto() {

    }

    public CustomerResponseDto(Customer customer) {
        setId(customer.getId());
        setCreated(customer.getCreated());
        setUpdated(customer.getUpdated());
        this.firstName = customer.getFirstName();
        this.lastName = customer.getSecondName();
        this.productCount = customer.getProducts().size();
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
