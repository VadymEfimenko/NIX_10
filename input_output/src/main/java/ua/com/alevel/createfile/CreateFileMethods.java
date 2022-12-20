package ua.com.alevel.createfile;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFileMethods {

    public void nioCreateFile(String filePath){
        Path file = Paths.get(filePath);

        try {
            Files.createFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ioCreateFile(String filePath){
        File file = new File(filePath);

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void outputStreamCreateFile(String filePath){
        try(BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath)) ) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void guavaCreateFile(String filePath){
        try {
            com.google.common.io.Files.touch(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void apacheCommonsCreateFile(String filePath){
        try {
            FileUtils.touch(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
