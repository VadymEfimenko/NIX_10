package ua.com.alevel.writetofile;

import ua.com.alevel.createfile.CreateFileMethods;

public class WriteToFileMain {

    public static void main(String[] args) {
        final String CONTENT_TO_WRITE = "In this tutorial, we'll explore different ways to write to a file using Java.";

        final String BUFFERED_WRITER_FILE = "input_output/src/main/java/ua/com/alevel/writetofile/bufferedWriterFile.txt";
        final String PRINT_WRITER_FILE = "input_output/src/main/java/ua/com/alevel/writetofile/printWriterFile.txt";
        final String FILE_OUTPUT_STREAM_FILE = "input_output/src/main/java/ua/com/alevel/writetofile/FileOutputStreamFile.txt";
        final String DATA_OUTPUT_STREAM_FILE = "input_output/src/main/java/ua/com/alevel/writetofile/DataOutputStream.txt";
        final String RANDOM_ACCESS_FILE = "input_output/src/main/java/ua/com/alevel/writetofile/RandomAccessFile.txt";
        final String FILE_CHANNEL_FILE = "input_output/src/main/java/ua/com/alevel/writetofile/FileChannelFIle.txt";
        final String FILES_CLASS_FILE = "input_output/src/main/java/ua/com/alevel/writetofile/FilesClassFile.txt";
        final String LOCK_FILE = "input_output/src/main/java/ua/com/alevel/writetofile/LockFile.txt";

        WriteToFileMethods writeToFileMethods = new WriteToFileMethods();
        CreateFileMethods createFileMethods = new CreateFileMethods();

        createFileMethods.guavaCreateFile(BUFFERED_WRITER_FILE);
        createFileMethods.guavaCreateFile(PRINT_WRITER_FILE);
        createFileMethods.guavaCreateFile(FILE_OUTPUT_STREAM_FILE);
        createFileMethods.guavaCreateFile(RANDOM_ACCESS_FILE);
        createFileMethods.guavaCreateFile(DATA_OUTPUT_STREAM_FILE);
        createFileMethods.apacheCommonsCreateFile(FILE_CHANNEL_FILE);
        createFileMethods.guavaCreateFile(FILES_CLASS_FILE);
        createFileMethods.guavaCreateFile(LOCK_FILE);


        writeToFileMethods.bufferedWriterWrite(CONTENT_TO_WRITE, BUFFERED_WRITER_FILE);
        writeToFileMethods.printWriterWrite(CONTENT_TO_WRITE, PRINT_WRITER_FILE);
        writeToFileMethods.fileOutputStreamWrite(CONTENT_TO_WRITE, FILE_OUTPUT_STREAM_FILE);
        writeToFileMethods.dataOutputStreamWrite(CONTENT_TO_WRITE, DATA_OUTPUT_STREAM_FILE);
        writeToFileMethods.randomAccessFileWrite(CONTENT_TO_WRITE, RANDOM_ACCESS_FILE);
        writeToFileMethods.fileChannelWrite(CONTENT_TO_WRITE, FILE_CHANNEL_FILE);
        writeToFileMethods.filesClassWrite(CONTENT_TO_WRITE, FILES_CLASS_FILE);
        writeToFileMethods.lockBeforeWriting(CONTENT_TO_WRITE, LOCK_FILE);


    }
}
