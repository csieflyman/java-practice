package nio;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;

import static java.lang.System.out;
import static java.nio.file.StandardWatchEventKinds.*;

/**
 * @author flyman
 */
public class NIO2Main {

    public static void main(String[] args) {
        System.out.println("===== FileSystem =====");
        testFileSystem();
        System.out.println("===== FileSystem =====");
        testFileStore();

        System.out.println("===== Path =====");
        testPath();
        System.out.println("===== File Attribute =====");
        testFileAttributes();
        System.out.println("===== WatchService =====");
        testWatchService();
    }

    private static void testFileSystem() {
        FileSystem fileSystem = FileSystems.getDefault();
        Iterable<Path> rootDirs = fileSystem.getRootDirectories();
        rootDirs.forEach(dir -> {
            out.println(dir);
        });
    }

    private static void testFileStore() {
        FileSystem fileSystem = FileSystems.getDefault();
        Iterable<FileStore> fileStores = fileSystem.getFileStores();
        DecimalFormat df = new DecimalFormat("#,###,###");
        fileStores.forEach(store -> {
            out.println(store.toString());
            try {
                out.printf("\t- TotalSpace = %s bytes%n", df.format(store.getTotalSpace()));
                out.printf("\t- UsableSpace = %s bytes%n", df.format(store.getUsableSpace()));
                out.printf("\t- UnallocatedSpace = %s bytes%n", df.format(store.getUnallocatedSpace()));
                out.println("\t- isReadOnly = " + store.isReadOnly());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static void testPath() {
        Path a = Paths.get("a");
        Path b = Paths.get("b");
        Path ab = a.resolve(b);
        Path ax = ab.resolveSibling("x");
        System.out.println("a resolve b = " + ab);
        System.out.println("ab resolveSibling x = " + ax);
        System.out.println("ab toAbsolutePath's root = " + ab.toAbsolutePath().getRoot());

        System.out.print("ab is Iterable = ");
        ab.forEach(p -> {System.out.print(p + ", ");}); // Path is Iterable
        System.out.println();

        Path abc = Paths.get("a", "b", "c");
        Path abd = Paths.get("a", "b", "d");
        Path ctod = abc.relativize(abd);
        System.out.println("abc relativize abd = " + ctod.toString());
    }

    private static void testFileAttributes() {
        try {
            Path file = Paths.get("nio2-est.txt");
            Files.deleteIfExists(file);
            file = Files.createFile(Paths.get("test.txt"));
            Map<String, Object> attrs = Files.readAttributes(file, "size,lastModifiedTime,lastAccessTime");
            attrs.forEach((key, value) -> {out.printf("key = %s, value = %s\n", key, value);});
            Files.setAttribute(file, "basic:lastModifiedTime", FileTime.fromMillis(System.currentTimeMillis()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Thread newWatchServiceDaemon(Path dir) {
        Thread watchServiceDaemon = new Thread(() -> {
            WatchService watchService = null;
            try {
                watchService = FileSystems.getDefault().newWatchService();
                WatchEvent.Kind[] kinds = (WatchEvent.Kind[]) Arrays.asList(ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY).toArray();
                dir.register(watchService, kinds);
            } catch (IOException e) {
                e.printStackTrace();
            }

            while(true) {
                WatchKey key = null;
                try {
                    key = watchService.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (WatchEvent<?> watchEvent : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = watchEvent.kind();
                    if(kind == OVERFLOW) {
                        return;
                    }
                    WatchEvent<Path> event = (WatchEvent<Path>) watchEvent;
                    Path fileName = event.context();
                    System.out.println(fileName + " " + kind.name());
                }

                boolean valid = key.reset();
                if(!valid) {
                    break;
                }
            }
        });
        watchServiceDaemon.setDaemon(true);
        return watchServiceDaemon;
    }

    private static void testWatchService() {
        try {
            Path dir = Paths.get("NIO2-test");
            if(Files.notExists(dir)) {
                dir = Files.createDirectory(Paths.get("NIO2-test"));
            }
            Thread watchServiceDaemon = newWatchServiceDaemon(dir);
            watchServiceDaemon.start();
            System.out.println("WatchService Start");

            Thread.sleep(5000);

            System.out.println("Start Changing");
            String fileName = "test.txt";
            String copyFileName = "test-copy.txt";
            Path file = Files.write(dir.resolve(fileName).toAbsolutePath(), Arrays.asList("line1", "line2"), StandardCharsets.UTF_8);
            Path copyFile = Files.copy(file, dir.resolve(copyFileName), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Finish Changing");

            Thread.sleep(5000);
            System.out.println("WatchService Stop");
            System.out.println("Main Thread Stop");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Main Thread Stop");
        }
    }
}
