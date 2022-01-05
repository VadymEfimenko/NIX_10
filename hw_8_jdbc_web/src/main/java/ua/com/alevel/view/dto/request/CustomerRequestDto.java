package ua.com.alevel.view.dto.request;

import java.util.List;

public class CustomerRequestDto extends RequestDto {

    private String firstName;
    private String lastName;
    private List<Integer> productsId;

    public List<Integer> getProductsId() {
        return productsId;
    }

    public void setProductsId(List<Integer> productsId) {
        this.productsId = productsId;
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

    @Override
    public String toString() {
        return "CustomerRequestDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
