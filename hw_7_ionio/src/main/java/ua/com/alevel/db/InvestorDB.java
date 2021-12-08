package ua.com.alevel.db;

import com.google.gson.Gson;
import com.sun.source.doctree.SeeTree;
import ua.com.alevel.entity.Investor;
import ua.com.alevel.entity.Company;
import ua.com.alevel.util.GenerateIdUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InvestorDB {

    private static InvestorDB instance;
    private final List<Investor> investors;
    Gson gson = new Gson();

    private InvestorDB() {
        investors = new ArrayList<>();
    }

    public static InvestorDB getInstance() {
        if (instance == null) {
            instance = new InvestorDB();
        }
        return instance;
    }

    public void create(Investor investor) {
        investor.setId(GenerateIdUtil.generateId(investors));
        investors.add(investor);
        String json = gson.toJson(investors);
        try {
            FileWriter writer = new FileWriter("investors.json");
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Investor investor) {
        Investor current = findById(investor.getId());
        current.setName(investor.getName());
        current.setCompanies(investor.getCompanies());
        String json = gson.toJson(investors);
        try {
            FileWriter writer = new FileWriter("investors.json");
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void delete(String id) {
        investors.removeIf(investor -> investor.getId().equals(id));
        String json = gson.toJson(investors);
        try {
            FileWriter writer = new FileWriter("investors.json");
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Investor findById(String id) {
        Investor investorById = investors.stream()
                .filter(investor -> investor.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("user not found"));
        String json = gson.toJson(investorById);
        try {
            FileWriter writer = new FileWriter("investorById.json");
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return investorById;
    }

    public void findAll() {
        String json = gson.toJson(investors);
        try {
            FileWriter writer = new FileWriter("allInvestors.json");
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
