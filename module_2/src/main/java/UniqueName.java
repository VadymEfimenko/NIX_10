import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class UniqueName {
    File file = new File("C:\\Users\\Admin\\IdeaProjects\\NIX_10\\module_2\\UniqueNames.txt");

    public void readFile() throws IOException {
        ArrayList<String> toRemove = new ArrayList<>();
        String fileToString = FileUtils.readFileToString(file);
        String[] splitString = fileToString.split("\\R+|\\s+|\\d+");
        ArrayList<String> strings = new ArrayList<>(Arrays.stream(splitString).toList());
        System.out.println("Second task. Names in file: " + strings);
        for (int i = 0; i < strings.size(); i++) {
            for (int j = i + 1; j < strings.size(); j++) {
                if (strings.get(i).equals(strings.get(j))){
                    toRemove.add(strings.get(i));
                }
            }
        }
        strings.removeAll(toRemove);
        System.out.println("First unique name: " + strings.get(0));
    }
}
