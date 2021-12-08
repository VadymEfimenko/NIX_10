package ua.com.alevel.db;

import ua.com.alevel.entity.Company;
import ua.com.alevel.entity.Investor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvestorCompanyDB {

    private static InvestorCompanyDB instance;
    private final List<Investor> investorCompany;

    private InvestorCompanyDB(){
        investorCompany = new ArrayList<>();
    }

    public InvestorCompanyDB getInstance(){
        if (instance == null){
            instance = new InvestorCompanyDB();
        }
        return instance;
    }

    public void findAll(){

    }
}
