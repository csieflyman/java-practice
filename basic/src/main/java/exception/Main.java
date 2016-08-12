package exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author flyman
 */
public class Main {

    public static void main(String[] args) {

    }

    private static void tryWithResource() {
        try(InputStream in = new FileInputStream("non-exist.txt"); ) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void earlyClose() throws IOException {
        InputStream in = null;
        Throwable ex = null;
        try{
            in = new FileInputStream("non-exist.txt");
        } catch (FileNotFoundException e) {
            ex = e;
            throw e; // more-precise-rethrow
        }
        finally {
            if(in != null) {
                if(ex != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        ex.addSuppressed(e); // suppression exception
                    }
                }
                else {
                    in.close();
                }
            }
        }
    }
}
