package ua.com.alevel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class BufferChannelIO {

    public static void main(String[] args) {

        try(RandomAccessFile dist = new RandomAccessFile("distBufferChannel.txt", "rw");
            RandomAccessFile source = new RandomAccessFile("sourceBufferChannel.txt", "rw");
            FileChannel distChannel = dist.getChannel();
            FileChannel sourceChannel = source.getChannel()) {
            distChannel.truncate(0);
            ByteBuffer buffer = ByteBuffer.allocate(1000);
            int bytesReadResult = sourceChannel.read(buffer);
            while (bytesReadResult > 0){
                buffer.flip();

                while (buffer.hasRemaining()){
                    distChannel.write(buffer);
                }
                buffer.clear();
                bytesReadResult = distChannel.read(buffer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
