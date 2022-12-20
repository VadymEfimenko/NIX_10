package ua.com.alevel;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path file = Paths.get("a_mainDirectory_NIO/a_file1.txt");
        Path file2 = Paths.get("a_mainDirectory_NIO/a_file2.txt");
        Path innerFile = Paths.get("a_mainDirectory_NIO/innerDirectory/innerFile.txt");
        Path directoryMain = Paths.get("a_mainDirectory_NIO");
        Path directoryDest = Paths.get("a_destination");
        Path innerDirectory = Paths.get("a_mainDirectory_NIO/innerDirectory");
        try {
            Files.createDirectory(directoryMain);
            Files.createDirectory(innerDirectory);
            Files.createDirectory(directoryDest);
            Files.createFile(file);
            Files.createFile(file2);
            Files.createFile(innerFile);
            Files.write(file, "File1 Hello world".getBytes());
            Files.write(file2, "File2 Goodbye World".getBytes());
            Files.write(innerFile, "Im inner file".getBytes());
            Buffer buffer = CharBuffer.allocate(100);

            printContentInFiles(file, file2, innerFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printContentInFiles(Path... paths) throws IOException {
        for (Path path : paths) {
            System.out.println(Files.readAllLines(path));
        }
    }
}