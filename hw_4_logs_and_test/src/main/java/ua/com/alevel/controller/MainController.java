package ua.com.alevel.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainController {
    public static void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select your option:");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.exit(0);
                }
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("Problem: = " + e.getMessage());
        }
    }

    private static void runNavigation(){
        System.out.println("Book controller: 1");
        System.out.println("Author controller : 2");
        System.out.println("EXIT -> 0");
    }

    private static void crud(String position, BufferedReader reader){
        switch (position){
            case "1" -> BookController.run();
            case "2" -> AuthorController.run();
        }
        runNavigation();
    }
}
