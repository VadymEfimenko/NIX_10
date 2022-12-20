package ua.com.alevel.createfile;

import java.io.File;

public class CreateFileMain {

    public static void main(String[] args) {
        final String FILE_PATH = "input_output/src/main/java/ua/com/alevel/createFile/file.txt";
        CreateFileMethods fileMethods = new CreateFileMethods();

        System.out.println("\ncreate file with IO File");
        deleteFile(FILE_PATH);
        fileExists(FILE_PATH);
        fileMethods.ioCreateFile(FILE_PATH);
        fileExists(FILE_PATH);

        System.out.println("\ncreate file with NIO Files");
        deleteFile(FILE_PATH);
        fileExists(FILE_PATH);
        fileMethods.nioCreateFile(FILE_PATH);
        fileExists(FILE_PATH);

        System.out.println("\ncreate file with FileInputStream");
        deleteFile(FILE_PATH);
        fileExists(FILE_PATH);
        fileMethods.outputStreamCreateFile(FILE_PATH);
        fileExists(FILE_PATH);

        System.out.println("\ncreate file with Guava");
        deleteFile(FILE_PATH);
        fileExists(FILE_PATH);
        fileMethods.guavaCreateFile(FILE_PATH);
        fileExists(FILE_PATH);

        System.out.println("\ncreate file with Apache Commons");
        deleteFile(FILE_PATH);
        fileExists(FILE_PATH);
        fileMethods.apacheCommonsCreateFile(FILE_PATH);
        fileExists(FILE_PATH);
    }

    public static void deleteFile(String filePath){
        File file = new File(filePath);
        file.delete();
    }

    public static void fileExists(String filePath){
        File file = new File(filePath);
        System.out.println(file.exists());
    }
}
