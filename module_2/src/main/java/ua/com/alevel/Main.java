package ua.com.alevel;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            //Task 1
            DatesFormat datesFormat = new DatesFormat();
            datesFormat.readToString();
            datesFormat.formatDateAndWrite();
            System.out.println("First task. Dates in your file : " + datesFormat.dateString);
            //Task 2
            UniqueName uniqueName = new UniqueName();
            uniqueName.readFile();
            //Task3
            CityController cityController = new CityController();
            cityController.readAndMakeListOfCities();
            cityController.initializeCities();
            cityController.setId();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
