package ua.com.alevel.readfile;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadFileMethods {

    public void bufferedReaderRead(String filePath){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            StringBuilder builder = new StringBuilder();
            String result;
            while ((result = bufferedReader.readLine()) !=null){
                builder.append(result).append("\n");
            }
            validation(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ;
    }
    public void commonsIOLibRead(String filePath){
        File file = new File(filePath);
        try {
            validation(FileUtils.readFileToString(file, "UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void classFilesSmallFileRead(String filePath){
        Path path = Paths.get(filePath);
        try {
            String result = Files.readAllLines(path).toString();
            System.out.println(result);
            validation(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void validation(String readFileText){
        System.out.println(readFileText.equals("""
                In this tutorial, we'll explore different ways to read from a File in Java.
                In this tutorial, we'll explore different ways to read from a File in Java.
                """));
    }
}
