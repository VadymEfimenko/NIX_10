package ua.com.alevel.db;

import ua.com.alevel.entity.Investor;

import java.util.ArrayList;
import java.util.List;

public class InvestorCompanyDB {

    private static InvestorCompanyDB instance;
    private final List<Investor> investorCompany;

    private InvestorCompanyDB() {
        investorCompany = new ArrayList<>();
    }

    public InvestorCompanyDB getInstance() {
        if (instance == null) {
            instance = new InvestorCompanyDB();
        }
        return instance;
    }

    public void findAll() {

    }
}
