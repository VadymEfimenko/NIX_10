package ua.com.alevel.view.dto.request;

import java.util.List;
import java.util.Set;

public class CustomerRequestDto extends RequestDto {

    private String firstName;
    private String secondName;
    private Set<Long> productsId;

    public Set<Long> getProductsId() {
        return productsId;
    }

    public void setProductsId(Set<Long> productsId) {
        this.productsId = productsId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public String toString() {
        return "CustomerRequestDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + secondName + '\'' +
                '}';
    }
}
