package ua.com.alevel.entity;

import java.util.Set;

public class Company extends BaseEntity {

    private String CompName;
    private Set<String> Investors;

    public String getCompName() {
        return CompName;
    }

    public void setCompName(String compName) {
        CompName = compName;
    }

    public Set<String> getInvestors() {
        return Investors;
    }

    public void setInvestors(Set<String> investors) {
        Investors = investors;
    }

}
