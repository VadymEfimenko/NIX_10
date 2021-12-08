package ua.com.alevel.entity;

import java.util.Set;

public class Investor extends BaseEntity {

    private String name;
    private Set<String> companies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<String> companies) {
        this.companies = companies;
    }
}
