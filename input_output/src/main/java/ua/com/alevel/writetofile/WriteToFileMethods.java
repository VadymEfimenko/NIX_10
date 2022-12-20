package ua.com.alevel.writetofile;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteToFileMethods {

    public void bufferedWriterWrite(String contentToWrite, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(contentToWrite);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printWriterWrite(String contentToWrite, String fileName) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(fileName))) {
            printWriter.write(contentToWrite);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void fileOutputStreamWrite(String contentToWrite, String fileName) {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName))) {
            bufferedOutputStream.write(contentToWrite.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void dataOutputStreamWrite(String contentToWrite, String fileName) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(fileOutputStream))) {
            dataOutputStream.writeUTF(contentToWrite);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void randomAccessFileWrite(String contentToWrite, String fileName) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw")) {
            randomAccessFile.write(contentToWrite.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void fileChannelWrite(String contentToWrite, String fileName) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
             FileChannel channel = randomAccessFile.getChannel()) {
            byte[] contentBytes = contentToWrite.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(contentBytes.length);
            buffer.put(contentBytes);
            buffer.flip();
            channel.write(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void filesClassWrite(String contentToWrite, String fileName) {
        Path path = Paths.get(fileName);
        byte[] bytesContent = contentToWrite.getBytes();

        try {
            Files.write(path, bytesContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void lockBeforeWriting(String contentToWrite, String fileName) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
             FileChannel channel = randomAccessFile.getChannel()) {
            FileLock lock = channel.tryLock();
            randomAccessFile.write(contentToWrite.getBytes());
            lock.release();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
