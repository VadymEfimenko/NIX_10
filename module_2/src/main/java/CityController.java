import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CityController {

    File inputCities = new File("C:\\Users\\Admin\\IdeaProjects\\NIX_10\\module_2\\InputCities.txt");
    List<String> cities = new ArrayList<>();
    String citiesData;
    ArrayList<Integer> indexesOfCityInText = new ArrayList<>();

    public void readAndMakeListOfCities() throws IOException {
        citiesData = FileUtils.readFileToString(inputCities);
        String[] strings = citiesData.split("\\R|\\s");
        List<String> citiesD = Arrays.stream(strings).toList();
        System.out.println(citiesD);
        for (int i = 0; i < citiesD.size(); i++) {
            if (StringUtils.isAlpha(citiesD.get(i))) {
                cities.add(citiesD.get(i));
            }
        }
        System.out.println(cities);
    }
}
