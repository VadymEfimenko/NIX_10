package ua.com.alevel.readfile;

import ua.com.alevel.createfile.CreateFileMethods;
import ua.com.alevel.writetofile.WriteToFileMethods;

public class ReadFileMain {

    public static void main(String[] args) {
        final String READ_FILE_PATH = "input_output/src/main/java/ua/com/alevel/readfile/ReadFile.txt";
        final String CONTENT_TO_WRITE = """
                In this tutorial, we'll explore different ways to read from a File in Java.
                In this tutorial, we'll explore different ways to read from a File in Java.
                """;


        CreateFileMethods createFile = new CreateFileMethods();
        WriteToFileMethods writeToFile = new WriteToFileMethods();
        ReadFileMethods readFile = new ReadFileMethods();

        createFile.guavaCreateFile(READ_FILE_PATH);
        writeToFile.fileChannelWrite(CONTENT_TO_WRITE, READ_FILE_PATH);


        readFile.commonsIOLibRead(READ_FILE_PATH);
        readFile.bufferedReaderRead(READ_FILE_PATH);
        readFile.classFilesSmallFileRead(READ_FILE_PATH);
    }



}

