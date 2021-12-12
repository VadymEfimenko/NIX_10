package ua.com.alevel.db;

import com.google.gson.Gson;
import ua.com.alevel.entity.Company;
import ua.com.alevel.util.GenerateIdUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDB {

    private final List<Company> companies;
    private static CompanyDB instance;
    Gson gson = new Gson();

    private CompanyDB() {
        companies = new ArrayList<>();
    }

    public static CompanyDB getInstance() {
        if (instance == null) {
            instance = new CompanyDB();
        }
        return instance;
    }

    public void create(Company company) {
        company.setId(GenerateIdUtil.generateId(companies));
        companies.add(company);
        String json = gson.toJson(companies);
        try {
            FileWriter fileWriter = new FileWriter("companies.json");
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Company company) {
        Company current = findById(company.getId());
        current.setCompName(company.getCompName());
        current.setInvestors(company.getInvestors());
        String json = gson.toJson(companies);
        try {
            FileWriter fileWriter = new FileWriter("companies.json");
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void delete(String id) {
        companies.removeIf(company -> company.getId().equals(id));
        String json = gson.toJson(companies);
        try {
            FileWriter fileWriter = new FileWriter("companies.json");
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Company findById(String id) {
        Company companyById = companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("user not found"));
        String json = gson.toJson(companyById);
        try {
            FileWriter fileWriter = new FileWriter("companyById.json");
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return companyById;
    }

    public void findAll() {
        String json = gson.toJson(companies);
        try {
            FileWriter writer = new FileWriter("allCompanies.json");
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
