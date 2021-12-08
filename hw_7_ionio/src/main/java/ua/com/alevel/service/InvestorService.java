package ua.com.alevel.service;

import ua.com.alevel.dao.InvestorDAO;
import ua.com.alevel.entity.Investor;

public class InvestorService {

    private final InvestorDAO investorDAO = new InvestorDAO();

    public void create(Investor investor){
        investorDAO.create(investor);
    }

    public void update(Investor investor){
        investorDAO.update(investor);
    }

    public void delete(String id){
        investorDAO.delete(id);
    }

    public void findById(String id){
        investorDAO.findById(id);
    }

    public void findAll(){
        investorDAO.findAll();
    }
}
