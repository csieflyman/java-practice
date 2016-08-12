package io;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

/**
 * @author flyman
 */
public class Main {

    public static void main(String[] args) {
        testByte();
    }

    private static void testByte() {
        String fileName = "google.txt";
        String url = "http://www.google.com.tw";
        copyDataFromURLToFile(url, fileName);
        copyDataFromFileToFile(fileName);
        copyDataFromFileToConsole(fileName);
        writeDataFromFileToObjectFile(fileName);
    }

    private static void copy(InputStream in, OutputStream out) {
        try {
            byte[] data = new byte[1024];
            int readLength;
            while((readLength = in.read(data)) != -1) {
                out.write(data, 0, readLength);
            }
            out.flush();
        } catch (IOException e) {
            throw new UncheckedIOException("fail to copy", e);
        }
    }

    private static void copyDataFromFileToFile(String fileName) {
        try (InputStream in = new FileInputStream(fileName); OutputStream out = new FileOutputStream(fileName + "-out")) {
            copy(in, out);
        } catch (IOException e) {
            throw new UncheckedIOException(fileName, e);
        }
    }

    private static void copyDataFromURLToFile(String url, String fileName) {
        try(InputStream in = new URL(url).openStream();OutputStream out = new FileOutputStream(fileName)) {
            copy(in, out);
        } catch (IOException e) {
            throw new UncheckedIOException(fileName, e);
        }
    }

    private static void copyDataFromFileToConsole(String fileName) {
        try(Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException(fileName, e);
        }
    }

    private static void writeDataFromFileToObjectFile(String fileName) {
        try(InputStream in = new FileInputStream(fileName); DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName + "-out-object"))) {
            out.writeInt(1);
            out.writeBoolean(true);
            out.writeFloat(3.14f);
            out.writeUTF("data");
        } catch (IOException e) {
            throw new UncheckedIOException(fileName, e);
        }
    }
}
