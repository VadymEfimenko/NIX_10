package ua.com.alevel;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatesFormat {
    private static final Pattern DATE_PATTERN = Pattern.compile(
            "((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))(\\W)02(\\W)29)"
                    + "|(((19|2[0-9])[0-9]{2})(\\W)02(\\W)(0[1-9]|1[0-9]|2[0-8]))"
                    + "|(((19|2[0-9])[0-9]{2})(\\W)(0[13578]|10|12)(\\W)(0[1-9]|[12][0-9]|3[01]))"
                    + "|(((19|2[0-9])[0-9]{2})(\\W)(0[469]|11)(\\W)(0[1-9]|[12][0-9]|30))"
                    + "|(02(\\W)29(\\W)(2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26]))))"
                    + "|(02(\\W)(0[1-9]|1[0-9]|2[0-8])(\\W)((19|2[0-9])[0-9]{2}))"
                    + "|((0[13578]|10|12)(\\W)(0[1-9]|[12][0-9]|3[01])(\\W)((19|2[0-9])[0-9]{2}))"
                    + "|((0[469]|11)(\\W)(0[1-9]|[12][0-9]|30)(\\W)((19|2[0-9])[0-9]{2}))");

    File fileInput = new File("C:\\Users\\Admin\\IdeaProjects\\NIX_10\\module_2\\DateFormatInput.txt");
    File fileOutput = new File("C:\\Users\\Admin\\IdeaProjects\\NIX_10\\module_2\\DateFormatOutput.txt");
    ArrayList<String> dateString = new ArrayList<>();

    public void readToString() throws IOException {
        String file = FileUtils.readFileToString(fileInput);
        Matcher matcher = DATE_PATTERN.matcher(file);
        while (matcher.find()) {
            String temp = file.substring(matcher.start(), matcher.end());
            dateString.add(temp.replaceAll("\\W", "."));
        }
    }

    public void formatDateAndWrite() throws IOException {
        ArrayList<String> formattedDates = new ArrayList<>();
        for (String s : dateString) {
            String[] split = s.split("\\.");
            if (split[0].length() == 2){
                String temp = split[0];
                split[0] = split[2];
                split[2] = temp;
                formattedDates.add(String.join("", split));
            } else {
                formattedDates.add(s.replaceAll("\\.", ""));
            }
        }
        FileUtils.writeLines(fileOutput, formattedDates);
    }
}

