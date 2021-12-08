package ua.com.alevel.dao;

import ua.com.alevel.db.CompanyDB;
import ua.com.alevel.entity.Company;

public class CompanyDAO {

    public void create(Company company){
        CompanyDB.getInstance().create(company);
    }

    public void update(Company company){
        CompanyDB.getInstance().update(company);
    }

    public void delete(String id){
        CompanyDB.getInstance().delete(id);
    }

    public void findById(String id){
        CompanyDB.getInstance().findById(id);
    }

    public void findAll(){
        CompanyDB.getInstance().findAll();
    }
}
