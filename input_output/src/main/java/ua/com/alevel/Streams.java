package ua.com.alevel;

import java.io.*;

public class Streams {

    public static void main(String[] args) {

        File sourceFile = new File("sourceBufferChannel.txt");
        File distFile = new File("distBufferChannel.txt");
        try(InputStream sourceInputStream = new BufferedInputStream(new FileInputStream(sourceFile));
            OutputStream distOutputStream = new BufferedOutputStream(new FileOutputStream(distFile))
            ){
            distOutputStream.write(sourceInputStream.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
