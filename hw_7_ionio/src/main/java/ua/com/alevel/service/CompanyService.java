package ua.com.alevel.service;

import ua.com.alevel.dao.CompanyDAO;
import ua.com.alevel.entity.Company;

public class CompanyService {

    private final CompanyDAO companyDAO = new CompanyDAO();

    public void create(Company company){
        companyDAO.create(company);
    }

    public void update(Company company){
        companyDAO.update(company);
    }

    public void delete(String id){
        companyDAO.delete(id);
    }

    public void findById(String id){
        companyDAO.findById(id);
    }

    public void findAll(){
        companyDAO.findAll();
    }
}
