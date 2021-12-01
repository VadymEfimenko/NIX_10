package ua.com.alevel;

import java.util.HashMap;

public class City {
    int id;
    String name;
    HashMap<Integer, Integer> mapCosts;
    City(String name){
        this.name = name;
    }
}
