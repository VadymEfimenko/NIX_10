package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name = "product_name")
    private String name;
    private Integer price;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.REMOVE
    })
    @JoinTable(name = "product_customer",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private Set<Customer> customers;

    public Product() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
        customer.getProducts().add(this);
    }

    public void removeCustomer(Customer customer){
        customers.remove(customer);
        customer.getProducts().remove(this);
    }
}
