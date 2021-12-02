package ua.com.alevel;

import java.util.HashMap;
import java.util.List;

public class City {
    int id;
    String name;
    HashMap<Integer, Integer> mapCosts;
    int neighboursCount;
    List<Integer> neighborsId;
    City(String name){
        this.name = name;
    }
}
