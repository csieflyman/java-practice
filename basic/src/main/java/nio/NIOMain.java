package nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author flyman
 */
public class NIOMain {
    public static void main(String[] args) {
        testByteChannel();
        testFileChannel();
        testFileChannel2();
    }

    private static void testByteChannel() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try(ReadableByteChannel reader  = Channels.newChannel(new ByteArrayInputStream("test".getBytes(StandardCharsets.UTF_8)));
            WritableByteChannel writer = Channels.newChannel(new FileOutputStream("nio-test.txt"));) {
            while(reader.read(buffer) != -1) {
                buffer.flip();
                writer.write(buffer);
                buffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testFileChannel() {
        try(FileInputStream fi = new FileInputStream("nio-test.txt"); FileChannel fc = fi.getChannel()) {
            MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
            CharBuffer charBuffer = decoder.decode(buffer);
            String content = new String(charBuffer.array());
            System.out.println("content = " + content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testFileChannel2() {
        try(FileInputStream fi = new FileInputStream("nio-test.txt");FileChannel readChannel = fi.getChannel();
            WritableByteChannel writeChannel = Channels.newChannel(new FileOutputStream("nio-test-2.txt"))) {
            CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            while(readChannel.read(buffer) != -1) {
                buffer.flip();
                CharBuffer charBuffer = decoder.decode(buffer);
                String content = new String(charBuffer.array());
                System.out.println("content = " + content);
                buffer.flip();
                writeChannel.write(buffer);
                buffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
