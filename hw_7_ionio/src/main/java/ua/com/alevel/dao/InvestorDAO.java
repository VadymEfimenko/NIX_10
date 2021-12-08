package ua.com.alevel.dao;

import ua.com.alevel.db.InvestorDB;
import ua.com.alevel.entity.Company;
import ua.com.alevel.entity.Investor;

public class InvestorDAO {

    public void create(Investor investor){
        InvestorDB.getInstance().create(investor);
    }

    public void update(Investor investor){
        InvestorDB.getInstance().update(investor);
    }

    public void delete(String id){
        InvestorDB.getInstance().delete(id);
    }

    public void findById(String id){
        InvestorDB.getInstance().findById(id);
    }

    public void findAll(){
        InvestorDB.getInstance().findAll();
    }
}
