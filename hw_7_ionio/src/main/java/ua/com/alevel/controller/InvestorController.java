package ua.com.alevel.controller;

import ua.com.alevel.entity.Company;
import ua.com.alevel.entity.Investor;
import ua.com.alevel.service.CompanyService;
import ua.com.alevel.service.InvestorService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class InvestorController {

    private final InvestorService investorService = new InvestorService();
    private final CompanyService companyService = new CompanyService();

    private static void runNavigation() {
        System.out.println("Create investor: 1");
        System.out.println("Update investor: 2");
        System.out.println("Delete investor: 3");
        System.out.println("Find by id investor: 4");
        System.out.println("Find all investors: 5");
        System.out.println("EXIT -> 0");
    }

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select your option:");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
                position = reader.readLine();
                if (position.equals("0")) {
                    return;
                }
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("Problem: = " + e.getMessage());
        }
    }

    private void crud(String position, BufferedReader reader) {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findById(reader);
            case "5" -> findAll(reader);
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        System.out.println("investor create");
        try {
            System.out.println("enter investor name");
            String name = reader.readLine();
            System.out.println("enter company");
            String companyName = reader.readLine();
            Investor investor = new Investor();
            Company company = new Company();
            Set<String> companies = new HashSet<>();
            Set<String> investors = new HashSet<>();
            company.setCompName(companyName);
            investor.setName(name);
            investor.setCompanies(companies);
            company.setInvestors(investors);
            investorService.create(investor);
            companyService.create(company);
            investor.getCompanies().add(company.getId());
            company.getInvestors().add(investor.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void update(BufferedReader reader) {
        System.out.println("investor update");
        try {
            System.out.println("enter id");
            String id = reader.readLine();
            System.out.println("enter investor name");
            String name = reader.readLine();
            System.out.println("enter company name");
            Set<String> companies = new HashSet<>();
            Set<String> investors = new HashSet<>();
            Company company = new Company();
            Investor investor = new Investor();
            String companyName = reader.readLine();
            company.setCompName(companyName);
            company.setInvestors(investors);
            companyService.create(company);
            companies.add(company.getId());
            investor.setId(id);
            investor.setName(name);
            investor.setCompanies(companies);
            investorService.update(investor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void delete(BufferedReader reader) {
        System.out.println("investor delete");
        try {
            System.out.println("enter id");
            String id = reader.readLine();
            investorService.delete(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findById(BufferedReader reader) {
        System.out.println("investor find by id");
        try {
            System.out.println("enter id");
            String id = reader.readLine();
            investorService.findById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findAll(BufferedReader reader) {
        System.out.println("find all");
        investorService.findAll();
    }
}
