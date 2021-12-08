package ua.com.alevel;

import ua.com.alevel.controller.InvestorController;
import ua.com.alevel.controller.IoNioController;
import ua.com.alevel.db.CompanyDB;
import ua.com.alevel.db.InvestorCompanyDB;
import ua.com.alevel.db.InvestorDB;
import ua.com.alevel.entity.Company;
import ua.com.alevel.entity.Investor;

import java.util.HashSet;
import java.util.Set;

public class IoNioMain {
    public static void main(String[] args) {
        IoNioController ioNioController = new IoNioController();
        ioNioController.run();
    }
}
